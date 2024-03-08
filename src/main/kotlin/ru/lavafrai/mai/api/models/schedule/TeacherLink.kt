package ru.lavafrai.mai.api.models.schedule

import kotlinx.serialization.Serializable

@Serializable
data class TeacherLink (
    val name: String,
    val link: String,
)
