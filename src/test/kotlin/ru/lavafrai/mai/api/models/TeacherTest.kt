package ru.lavafrai.mai.api.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import ru.lavafrai.mai.api.MaiApi
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.TeacherId

class TeacherTest {
    @Test
    fun `Parse teacher links`() {
        MaiApi.getSchedule(Group("М4О-106Б-23"))
        println(Json.encodeToString(MaiApi.getTeachersList()))
    }

    @Test
    fun `Parse teacher schedule`() {
        val teacherLink = TeacherId("Нелин Игорь Владимирович", "cd3a00a1-1d9a-11e0-9baf-1c6f65450efa")
        println(Json.encodeToString(MaiApi.getTeachersSchedule(teacherLink)))
    }
}
