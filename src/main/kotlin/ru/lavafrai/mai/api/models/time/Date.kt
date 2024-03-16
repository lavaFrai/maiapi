package ru.lavafrai.mai.api.models.time

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class Date (
    val year: Int = 0,
    val month: Short = 0,
    val day: Short = 0,
) {
    fun isLaterThan(another: Date): Boolean {
        return if (year == another.year) {
            if (month == another.month) {
                day > another.day
            } else month > another.month
        } else year > another.year
    }

    fun isEarlierThan(another: Date): Boolean {
        return !(isLaterThan(another) || isSame(another))
    }

    fun isSame(another: Date): Boolean {
        return  year == another.year &&
                month == another.month &&
                day == another.day
    }

    operator fun compareTo(another: Date): Int {
        return when {
            isSame(another) -> 0
            isLaterThan(another) -> 1
            isEarlierThan(another) -> -1
            else -> 0
        }
    }

    override fun toString(): String {
        return "${day.toString().padStart(2, '0')}.${month.toString().padStart(2, '0')}.$year"
    }

    companion object {
        fun now(): Date {
            val calendar = Calendar.getInstance()
            return Date(
                calendar.get(Calendar.YEAR),
                (calendar.get(Calendar.MONTH) + 1).toShort(),
                calendar.get(Calendar.DAY_OF_MONTH).toShort(),
            )
        }

        fun parse(string: String): Date {
            val match = "(\\d{2})\\.(\\d{2})\\.(\\d{4})".toRegex().find(string)!!
            return Date(
                match.groups[3]!!.value.toInt(),
                match.groups[2]!!.value.toShort(),
                match.groups[1]!!.value.toShort(),
            )
        }

        fun of(calendar: Calendar): Date {
            return Date(
                year = calendar.get(Calendar.YEAR),
                month = (calendar.get(Calendar.MONTH) + 1).toShort(),
                day = calendar.get(Calendar.DAY_OF_MONTH).toShort(),
            )
        }

        fun ofYearLess(calendar: Calendar): Date {
            return Date(
                month = (calendar.get(Calendar.MONTH) + 1).toShort(),
                day = calendar.get(Calendar.DAY_OF_MONTH).toShort(),
            )
        }

        fun parseMaiFormat(date: String): Date {
            return Date.parse("${date.substring(6..7)}.${date.substring(4..5)}.${date.substring(0..3)}")
        }
    }
}