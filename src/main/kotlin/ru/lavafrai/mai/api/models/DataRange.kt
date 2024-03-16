package ru.lavafrai.mai.api.models

import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.time.Date


@Serializable
class DateRange(
    val startDate: Date,
    val endDate: Date,
) {
    operator fun contains(another: Date): Boolean {
        return startDate <= another && endDate >= another
    }


    override fun toString(): String {
        return "${startDate.toString()} - ${endDate.toString()}"
    }

    fun isNow(): Boolean {
        return Date.now() in this
    }


    companion object {
        fun parse(string: String): DateRange {
            return DateRange(
                Date.parse(string.split(" - ")[0]),
                Date.parse(string.split(" - ")[1]),
            )
        }
    }
}
