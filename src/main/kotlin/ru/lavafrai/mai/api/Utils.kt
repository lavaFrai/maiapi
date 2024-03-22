package ru.lavafrai.mai.api

infix fun <T> (() -> T).errorCase(onError: () -> T): T {
    return try {
        this()
    } catch (e: Exception) {
        onError()
    }
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.lowercase().capitalize() }