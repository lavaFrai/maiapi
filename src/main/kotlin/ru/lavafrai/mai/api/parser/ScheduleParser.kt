package ru.lavafrai.mai.api.parser

import org.jsoup.nodes.Element
import ru.lavafrai.mai.api.SCHEDULE_PAGE_URL
import ru.lavafrai.mai.api.models.SerializableDate
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.*
import ru.lavafrai.mai.api.models.schedule.network.getPage
import ru.lavafrai.mai.api.models.schedule.network.getSchedulePage
import java.time.DayOfWeek
import java.util.*


fun parseSchedule(group: Group, teachers: MutableList<TeacherLink> = mutableListOf()) : Schedule {
    val weeks = parseScheduleParseWeeks(group)

    val schedules = weeks.map {
        parseScheduleParseWeek(group, it, teachers)
    }

    return Schedule(group, schedules);
}


fun parseTeacherSchedule(link: TeacherLink, teachers: MutableList<TeacherLink> = mutableListOf()) : Schedule {
    val weeks = parseScheduleParseWeeksByUrl(link)

    val schedules = weeks.map {
        parseScheduleParseWeekByUrl("https://mai.ru/" + link.link, it, teachers)
    }

    return Schedule(Group(link.name), schedules);
}


fun parseScheduleParseWeekByUrl(url: String, weekId: ScheduleWeekId, teachers: MutableList<TeacherLink>): OneWeekSchedule {
    val page = getPage(url)

    val lessons = page.select(".step").select(".step-content").map {
        subParseOneDaySchedule(it, teachers)
    }
    return OneWeekSchedule(weekId, lessons)
}

fun parseScheduleParseWeeksByUrl(link: TeacherLink): List<ScheduleWeekId> {
    val page = getPage("https://mai.ru/" + link.link)

    return page.select("#collapseWeeks").select(".list-group-item").map { parseScheduleWeek(it.text()) }
}


fun parseScheduleParseWeek(group: Group, weekId: ScheduleWeekId, teachers: MutableList<TeacherLink>): OneWeekSchedule {
    val page = getSchedulePage(mapOf("group" to group.name, "week" to weekId.number.toString()))

    val lessons = page.select(".step").select(".step-content").map {
        subParseOneDaySchedule(it, teachers)
    }
    return OneWeekSchedule(weekId, lessons)
}

fun subParseOneDaySchedule(page: Element, teachers: MutableList<TeacherLink>): OneDaySchedule {
    val day = page.select(".step-title").text()
    val dayOfWeek = when {
        day.startsWith("Пн") -> DayOfWeek.MONDAY
        day.startsWith("Вт") -> DayOfWeek.TUESDAY
        day.startsWith("Ср") -> DayOfWeek.WEDNESDAY
        day.startsWith("Чт") -> DayOfWeek.THURSDAY
        day.startsWith("Пт") -> DayOfWeek.FRIDAY
        day.startsWith("Сб") -> DayOfWeek.SATURDAY
        day.startsWith("Вс") -> DayOfWeek.SUNDAY
        else -> DayOfWeek.SUNDAY
    }
    val date = day.subSequence(4, day.length) as String
    val lessons = page.select(".step-content > div").map { subParseLesson(it, teachers) }
    val dayMatch = "(\\d+)[\\s ]+(\\S+)".toRegex().find(date)

    val monthStr = dayMatch!!.groups[2]!!.value
    val dayMonth = when {
        monthStr.startsWith("января") -> 1
        monthStr.startsWith("февраля") -> 2
        monthStr.startsWith("марта") -> 3
        monthStr.startsWith("апреля") -> 4
        monthStr.startsWith("мая") -> 5
        monthStr.startsWith("июня") -> 6
        monthStr.startsWith("июля") -> 7
        monthStr.startsWith("августа") -> 8
        monthStr.startsWith("сентября") -> 9
        monthStr.startsWith("октября") -> 10
        monthStr.startsWith("ноября") -> 11
        monthStr.startsWith("декабря") -> 12
        else -> 0
    }
    val dayDay = dayMatch.groups[1]!!.value.toShort()

    return OneDaySchedule(lessons, dayOfWeek, SerializableDate(Calendar.getInstance().get(Calendar.YEAR), dayMonth.toShort(), dayDay))
}

fun subParseLesson(page: Element, teachers: MutableList<TeacherLink>): ScheduleLesson {
    val type = when (page.select(".badge").text()) {
        "ЛК" -> ScheduleLessonType.LECTURE
        "ПЗ" -> ScheduleLessonType.SEMINAR
        "ЛР" -> ScheduleLessonType.LABORATORY
        "Экзамен" -> ScheduleLessonType.EXAM
        else -> ScheduleLessonType.Unknown
    }
    val name = page.child(0).text().removeSuffix(" ${page.select(".badge").text()}")
    val timeRange = page.child(1).child(0).text()

    val teacherFinder = "(?>(?>(?!\\d)\\S)+\\s){3}".toRegex()
    val teacher = teacherFinder.findAll(page.child(1).text()).joinToString(separator = " / ") { it.value.trim().lowercase().capitalizeWords() }
    page.child(1).select("a").forEach {
        teachers.add(TeacherLink(it.text(), it.attr("href")))
    }

    //println(page.child(1).text())

    val location: String = page.child(1).select(".fa-map-marker-alt").joinToString(separator = " / ") { it.parent().text() }

    return ScheduleLesson(name, timeRange, type, teacher, location)
}

fun parseScheduleParseWeeks(group: Group): List<ScheduleWeekId> {
    val page = getSchedulePage(mapOf("group" to group.name))

    return page.select("#collapseWeeks").select(".list-group-item").map { parseScheduleWeek(it.text()) }
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }
