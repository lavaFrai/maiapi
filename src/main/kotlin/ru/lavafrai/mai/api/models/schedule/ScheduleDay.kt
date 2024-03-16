package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.mai.api.models.time.DayOfWeek

@Serializable
data class ScheduleDay(
    val date: Date? = null,
    @SerialName("day") val dayOfWeek: DayOfWeek,
    val lessons: List<Lesson>
)
