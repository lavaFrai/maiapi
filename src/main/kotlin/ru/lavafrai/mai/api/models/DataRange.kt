package ru.lavafrai.mai.api.models

import kotlinx.serialization.Serializable


@Serializable
class DateRange(
    val startDate: SerializableDate,
    val endDate: SerializableDate,
) {
    operator fun contains(another: SerializableDate): Boolean {
        return startDate <= another && endDate >= another
    }


    override fun toString(): String {
        return "${startDate.toString()} - ${endDate.toString()}"
    }

    fun isNow(): Boolean {
        return SerializableDate.now() in this
    }


    companion object {
        fun parse(string: String): DateRange {
            return DateRange(
                SerializableDate.parse(string.split(" - ")[0]),
                SerializableDate.parse(string.split(" - ")[1]),
            )
        }
    }
}
