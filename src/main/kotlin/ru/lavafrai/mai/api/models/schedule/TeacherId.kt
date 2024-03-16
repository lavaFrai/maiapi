package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable

@Serializable
data class TeacherId(
    val name: String,
    val uid: String,
)