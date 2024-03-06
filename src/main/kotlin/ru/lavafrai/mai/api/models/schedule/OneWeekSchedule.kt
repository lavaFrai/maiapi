package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.DateRange
import ru.lavafrai.mai.api.models.SerializableDate
import java.time.DayOfWeek
import java.util.Calendar


@Serializable
data class OneWeekSchedule (
    val weekId: ScheduleWeekId,
    val days: List<OneDaySchedule>,
) {
    fun getTodayNumberOrInf(): Int {
        var today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        today -= 2
        if (today == -1) today = 6

        return today
    }
}

fun getEmptyOneWeekSchedule(): OneWeekSchedule {
    return OneWeekSchedule(
        ScheduleWeekId(0, DateRange.parse("09.02.2024 - 11.02.2024")),
        days = listOf(),
    )
}

fun getEmptyOneDaySchedule(date: SerializableDate): OneDaySchedule {
    return OneDaySchedule(
        lessons = listOf(),
        dayOfWeek = DayOfWeek.SUNDAY,
        date = date,
    )
}
