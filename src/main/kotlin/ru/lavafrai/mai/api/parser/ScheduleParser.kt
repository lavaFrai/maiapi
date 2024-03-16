package ru.lavafrai.mai.api.parser

import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.schedule.raw.toSchedule
import ru.lavafrai.mai.api.models.schedule.raw.toTeacherSchedule
import ru.lavafrai.mai.api.network.getPageToResponse
import java.math.BigInteger
import java.security.MessageDigest


fun parseSchedule(group: Group, teachers: MutableList<TeacherId> = mutableListOf()): Schedule? {
    val response = getPageToResponse("https://public.mai.ru/schedule/data/${md5(group.name)}.json")
    if (response.status != HttpStatusCode.OK) {
        println("Failed to parse $group"); return null
    }

    val schedule = runBlocking { response.bodyAsText() }.toSchedule()
    schedule.days.forEach { day ->
        day.lessons.forEach { lesson ->
            lesson.lectors.forEach { lector ->
                if (!teachers.contains(lector)) teachers.add(lector)
            }
        }
    }

    return schedule
}

fun parseTeacherSchedule(teacher: TeacherId, teachers: MutableList<TeacherId> = mutableListOf()): Schedule? {
    val response = getPageToResponse("https://public.mai.ru/schedule/data/${teacher.uid}.json")
    if (response.status != HttpStatusCode.OK) {
        println("Failed to parse ${teacher.name}"); return null
    }

    val schedule = runBlocking { response.bodyAsText() }.toTeacherSchedule()
    schedule.days.forEach { day ->
        day.lessons.forEach { lesson ->
            lesson.lectors.forEach { lector ->
                if (!teachers.contains(lector)) teachers.add(lector)
            }
        }
    }

    return schedule
}

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}