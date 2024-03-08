package ru.lavafrai.mai.api

import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.TeacherLink
import ru.lavafrai.mai.api.parser.parseGroupsList
import ru.lavafrai.mai.api.parser.parseSchedule
import ru.lavafrai.mai.api.parser.parseTeacherSchedule

class Api private constructor() {
    private val teachers: MutableList<TeacherLink> = ArrayList()


    fun getSchedule(group: Group): Schedule {
        return parseSchedule(group, teachers)
    }

    fun getGroups(): List<Group> {
        return parseGroupsList()
    }

    fun getTeachersSchedule(link: TeacherLink): Schedule {
        return parseTeacherSchedule(link)
    }

    fun getTeachersList(): List<TeacherLink> {
        return teachers
    }

    companion object {
        private var instance: Api? = null

        @Synchronized
        fun getInstance(): Api {
            if (instance == null) instance = Api()
            return instance!!
        }
    }
}