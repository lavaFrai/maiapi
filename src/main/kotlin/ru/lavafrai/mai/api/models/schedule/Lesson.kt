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
    val timeRange: String = "${timeStart.time.substring(0, timeStart.time.indexOf(":", 3))} – ${timeEnd.time.substring(0, timeEnd.time.indexOf(":", 3))}"
) {
    fun getPairNumber(): Int {
        return when (timeRange) {
            "09:00 – 10:30" -> 1
            "10:45 – 12:15" -> 2
            "13:00 – 14:30" -> 3
            "14:45 – 16:15" -> 4
            "16:30 – 18:00" -> 5
            "18:15 – 19:45" -> 6
            "20:00 – 21:30" -> 7
            else -> 0
        }
    }
}