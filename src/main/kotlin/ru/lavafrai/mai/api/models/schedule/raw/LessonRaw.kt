package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Classroom
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.LessonType


@Serializable
data class LessonRaw(
    val time_start: TimeRaw,
    val time_end: TimeRaw,
    val lector: Map<String, String>,
    val type: Map<LessonType, Int>,
    val room: Map<String, String>,
    val lms: String,
    val teams: String,
    val other: String,
) {
    fun toLesson(name: String): Lesson {
        return Lesson(
            name,
            time_start.toTime(),
            time_end.toTime(),
            lector.map { TeacherId(it.value, it.key) },
            type.map { it.key }.first(),
            room.map { Classroom(it.value, it.key) },
            lms,
            teams,
            other,
        )
    }
}