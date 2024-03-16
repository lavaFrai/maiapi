package ru.lavafrai.mai.api.models.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Group (
    val name: String,
    val fac: String? = null,
    val level: GroupType? = null,
)