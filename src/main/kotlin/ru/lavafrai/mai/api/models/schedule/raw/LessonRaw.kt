package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.capitalizeWords
import ru.lavafrai.mai.api.models.schedule.Classroom
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.LessonType


@Serializable
data class LessonRaw(
    @SerialName("time_start") val timeStart: TimeRaw,
    @SerialName("time_end") val timeEnd: TimeRaw,
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
            timeStart.toTime(),
            timeEnd.toTime(),
            lector.map { TeacherId(it.value.capitalizeWords(), it.key) },
            type.map { it.key }.first(),
            room.map { Classroom(it.value, it.key) },
            lms,
            teams,
            other,
        )
    }
}