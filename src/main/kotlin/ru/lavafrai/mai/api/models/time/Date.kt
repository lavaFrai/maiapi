package ru.lavafrai.mai.api.models.time

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.*


@Serializable
data class Date (
    val year: Int = 0,
    val month: Int = 0,
    val day: Int = 0,
) : Comparable<Date> {
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

    override operator fun compareTo(other: Date): Int {
        return when {
            isSame(other) -> 0
            isLaterThan(other) -> 1
            isEarlierThan(other) -> -1
            else -> 0
        }
    }

    fun toCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, this.year)
        calendar.set(Calendar.MONTH, this.month)
        calendar.set(Calendar.DAY_OF_MONTH, this.day)
        return calendar
    }

    fun toLocalDate(): LocalDate {
        return LocalDate.of(year, month, day)
    }

    fun getWeek(): DateRange {
        val monday = this.toLocalDate().minusDays(this.toLocalDate().dayOfWeek.value.toLong() - 1).toDate()
        val sunday = this.toLocalDate().plusDays(7 - this.toLocalDate().dayOfWeek.value.toLong()).toDate()
        return DateRange(monday, sunday)
    }

    override fun toString(): String {
        return "${day.toString().padStart(2, '0')}.${month.toString().padStart(2, '0')}.$year"
    }

    companion object {
        fun now(): Date {
            val calendar = Calendar.getInstance()
            return Date(
                calendar.get(Calendar.YEAR),
                (calendar.get(Calendar.MONTH) + 1),
                calendar.get(Calendar.DAY_OF_MONTH),
            )
        }

        fun parse(string: String): Date {
            val match = "(\\d{2})\\.(\\d{2})\\.(\\d{4})".toRegex().find(string)!!
            return Date(
                match.groups[3]!!.value.toInt(),
                match.groups[2]!!.value.toInt(),
                match.groups[1]!!.value.toInt(),
            )
        }

        fun of(calendar: Calendar): Date {
            return Date(
                year = calendar.get(Calendar.YEAR),
                month = (calendar.get(Calendar.MONTH) + 1),
                day = calendar.get(Calendar.DAY_OF_MONTH),
            )
        }

        fun ofYearLess(calendar: Calendar): Date {
            return Date(
                month = (calendar.get(Calendar.MONTH) + 1),
                day = calendar.get(Calendar.DAY_OF_MONTH),
            )
        }

        fun parseMaiFormat(date: String): Date {
            return parse("${date.substring(6..7)}.${date.substring(4..5)}.${date.substring(0..3)}")
        }
    }
}

fun Calendar.toDate(): Date {
    return Date.of(this)
}

fun LocalDate.toDate(): Date {
    return Date(year, monthValue, dayOfMonth)
}