package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.SerializableDate
import java.time.DayOfWeek

@Serializable
data class OneDaySchedule (
    val lessons: List<ScheduleLesson>,
    val dayOfWeek: DayOfWeek,
    val date: SerializableDate,
    val lessonsCount: Int = lessons.size
)
