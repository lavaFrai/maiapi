package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable

@Serializable
data class Classroom(
    val name: String,
    val uid: String,
)