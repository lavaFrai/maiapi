package ru.lavafrai.mai.api

import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.parser.parseGroupsList
import ru.lavafrai.mai.api.parser.parseSchedule
import ru.lavafrai.mai.api.parser.parseTeacherSchedule

object Api {
    private val teachers: MutableList<TeacherId> = ArrayList()


    fun getSchedule(group: Group): Schedule? {
        return parseSchedule(group, teachers)
    }

    fun getGroups(): List<Group> {
        return parseGroupsList().filter { it.name != "Для внеучебных мероприятий (служебная)" }
    }

    fun getTeachersSchedule(link: TeacherId): Schedule? {
        return parseTeacherSchedule(link)
    }

    fun getTeachersList(): List<TeacherId> {
        return teachers
    }
}