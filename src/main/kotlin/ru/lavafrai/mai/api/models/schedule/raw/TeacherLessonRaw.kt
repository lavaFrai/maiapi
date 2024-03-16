package ru.lavafrai.mai.api.models.schedule.raw

import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Classroom
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.LessonType
import ru.lavafrai.mai.api.parser.md5


@Serializable
data class TeacherLessonRaw(
    val name: String,
    val time_start: TimeRaw,
    val time_end: TimeRaw,
    val groups: List<String>,
    val types: List<LessonType>,
    val rooms: Map<String, String>,
) {
    fun toLesson(): Lesson {
        return Lesson(
            name,
            time_start.toTime(),
            time_end.toTime(),
            groups.map { TeacherId(it, md5(it)) },
            types.map { it }.first(),
            rooms.map { Classroom(it.value, it.key) },
            "",
            "",
            "",
        )
    }
}