package ru.lavafrai.mai.api.models.time

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class DayOfWeek {
    @SerialName("Пн") MONDAY,
    @SerialName("Вт") TUESDAY,
    @SerialName("Ср") WEDNESDAY,
    @SerialName("Чт") THURSDAY,
    @SerialName("Пт") FRIDAY,
    @SerialName("Сб") SATURDAY,
    @SerialName("Вс") SUNDAY;
}