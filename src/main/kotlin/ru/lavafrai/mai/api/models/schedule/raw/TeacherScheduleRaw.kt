package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.network.TolerantJson

typealias TeacherScheduleRaw = String

fun TeacherScheduleRaw.toTeacherSchedule(): Schedule {
    val jsonObject = Json.parseToJsonElement(this).jsonObject

    return Schedule(
        jsonObject["name"]?.jsonPrimitive?.content!!,
        0,
        0,
        jsonObject["schedule"]!!.jsonObject.map { TolerantJson.decodeFromJsonElement<TeacherScheduleDayRaw>(it.value).toScheduleDay(it.value.jsonObject["date"]?.jsonPrimitive?.content) }
    )
}
