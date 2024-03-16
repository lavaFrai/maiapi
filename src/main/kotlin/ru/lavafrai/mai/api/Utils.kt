package ru.lavafrai.mai.api

infix fun <T> (() -> T).errorCase(onError: () -> T): T {
    return try {
        this()
    } catch (e: Exception) {
        onError()
    }
}