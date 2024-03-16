package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable


@Serializable
data class Schedule (
    val name: String,
    val created: Long,
    val cached: Long,
    val days: List<ScheduleDay>,
) {
    /*fun getWeeks(): List<ScheduleWeekId> {
        return subSchedules.map { it.weekId }
    }

    fun getWeek(number: Int): OneWeekSchedule? {
        return subSchedules.find { it.weekId.number == number }
    }

    fun getCurrentSubScheduleOrNull(): OneWeekSchedule? {
        return subSchedules.find { SerializableDate.now() in it.weekId.range }
    }

    fun getScheduleOfDay(day: SerializableDate): OneDaySchedule {
        val week = subSchedules.find { day in it.weekId.range }
        week ?: return getEmptyOneDaySchedule(day)
        val daySchedule = week.days.find { day == it.date }
        return daySchedule ?: getEmptyOneDaySchedule(day)
    }*/
}

/*
fun getEmptySchedule(): Schedule {
    return Schedule(Group(""), 0, listOf())
}
*/