package ru.lavafrai.mai.api.models.group

import kotlinx.serialization.*
import kotlinx.serialization.json.Json


@Serializable
enum class GroupType {
    @SerialName("Бакалавриат") BACHELOR,
    @SerialName("Магистратура") MAGISTRACY,
    @SerialName("Аспирантура") POSTGRADUATE, // Аспирантура

    @SerialName("Специалитет") SPECIAL,
    @SerialName("Базовое высшее образование") BASE_HIGH,
    @SerialName("Специализированное высшее образование") SPECIAL_HIGH,
    @SerialName("") SERVICE,
}

fun main() {
    println(Json.encodeToString(GroupType.BACHELOR))
}