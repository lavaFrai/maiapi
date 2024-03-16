package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.time.Time

@Serializable
data class Lesson(
    val name: String,
    @SerialName("time_start") val timeStart: Time,
    @SerialName("time_end") val timeEnd: Time,
    val lectors: List<TeacherId>,
    val type: LessonType,
    val rooms: List<Classroom>,
    val lms: String,
    val teams: String,
    val other: String,
)