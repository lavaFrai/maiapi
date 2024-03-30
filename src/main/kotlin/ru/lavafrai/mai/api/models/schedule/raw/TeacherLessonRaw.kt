package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Classroom
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.LessonType
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.mai.api.parser.md5


@Serializable
data class TeacherLessonRaw(
    val name: String,
    @SerialName("time_start") val timeStart: TimeRaw,
    @SerialName("time_end") val timeEnd: TimeRaw,
    val groups: List<String>,
    val types: List<LessonType>,
    val rooms: Map<String, String>,
) {
    fun toLesson(day: Date?): Lesson {
        return Lesson(
            name,
            timeStart.toTime(),
            timeEnd.toTime(),
            groups.map { TeacherId(it, md5(it)) },
            types.map { it }.first(),
            day,
            rooms.map { Classroom(it.value, it.key) },
            "",
            "",
            "",
        )
    }
}