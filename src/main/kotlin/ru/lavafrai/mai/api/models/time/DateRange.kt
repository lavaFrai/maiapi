package ru.lavafrai.mai.api.models.time

import kotlinx.serialization.Serializable
import java.util.Calendar


@Serializable
data class DateRange(
    val startDate: Date,
    val endDate: Date,
) {
    operator fun contains(another: Date): Boolean {
        return another in startDate..endDate
    }


    override fun toString(): String {
        return "$startDate - $endDate"
    }

    fun isNow(): Boolean {
        return Date.now() in this
    }

    fun plus(field: Int, amount: Int): DateRange {
        return DateRange(
                startDate.plus(field, amount),
                endDate.plus(field, amount)
        )
    }

    fun minus(field: Int, amount: Int): DateRange {
        return plus(field, -amount)
    }

    fun plusDays(amount: Int): DateRange {
        return plus(Calendar.DATE, amount)
    }

    fun minusDays(amount: Int): DateRange {
        return minus(Calendar.DATE, amount)
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
