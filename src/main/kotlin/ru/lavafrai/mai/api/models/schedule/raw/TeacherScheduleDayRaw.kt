package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import ru.lavafrai.mai.api.models.schedule.ScheduleDay
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.mai.api.models.time.DayOfWeek
import ru.lavafrai.mai.api.network.TolerantJson

@Serializable
data class TeacherScheduleDayRaw (
    @SerialName("day") val dayOfWeek: DayOfWeek,
    @SerialName("pairs") val lessons: Map<String, JsonObject>
) {
    fun toScheduleDay(date: String? = null): ScheduleDay {
        return ScheduleDay(
            if (date != null) Date.parseMaiFormat(date) else null,
            dayOfWeek,
            lessons.map { TolerantJson.decodeFromJsonElement<TeacherLessonRaw>(it.value.jsonObject).toLesson() }
        )
    }
}
