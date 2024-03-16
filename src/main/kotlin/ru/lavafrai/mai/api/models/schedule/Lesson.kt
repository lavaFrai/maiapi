package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.time.Time

@Serializable
data class Lesson(
    val name: String,
    val time_start: Time,
    val time_end: Time,
    val lectors: List<TeacherId>,
    val type: LessonType,
    val rooms: List<Classroom>,
    val lms: String,
    val teams: String,
    val other: String,
)