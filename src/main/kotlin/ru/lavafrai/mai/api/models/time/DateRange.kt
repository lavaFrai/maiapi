package ru.lavafrai.mai.api.models.time

import kotlinx.serialization.Serializable


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


    companion object {
        fun parse(string: String): DateRange {
            return DateRange(
                Date.parse(string.split(" - ")[0]),
                Date.parse(string.split(" - ")[1]),
            )
        }
    }
}
