package ru.lavafrai.mai.api.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import ru.lavafrai.mai.api.Api
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.TeacherLink

class TeacherTest {
    @Test
    fun `Parse teacher links`() {
        val api = Api.getInstance()
        api.getSchedule(Group("М4О-106Б-23"))
        println(Json.encodeToString(api.getTeachersList()))
    }

    @Test
    fun `Parse teacher schedule`() {
        val api = Api.getInstance()
        val teacherLink = TeacherLink("Сыч", "/education/studies/schedule/ppc.php?guid=578c1818-1d99-11e0-9baf-1c6f65450efa")
        println(api.getTeachersSchedule(teacherLink))
    }
}
