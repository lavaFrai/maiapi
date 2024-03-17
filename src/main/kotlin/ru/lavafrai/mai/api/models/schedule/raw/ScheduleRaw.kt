package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.network.TolerantJson

typealias ScheduleRaw = String

fun ScheduleRaw.toSchedule(): Schedule {
    val jsonObject = Json.parseToJsonElement(this).jsonObject

    return Schedule(

        jsonObject["group"]?.jsonPrimitive?.content!!,
        System.currentTimeMillis() / 1000,
        0,
        jsonObject.filter { it.key != "group" }.map { TolerantJson.decodeFromJsonElement<ScheduleDayRaw>(it.value).toScheduleDay(it.key) }
    )
}
