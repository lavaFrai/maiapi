package ru.lavafrai.mai.api.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.jsoup.nodes.Document
import ru.lavafrai.mai.api.SCHEDULE_PAGE_URL
import java.io.IOException

fun getPage(url: String, args: Map<String, String> = mapOf(), attemptsLeft: Int = 50): String {
    var builtUrl = url
    if (args.isNotEmpty()) {
        if (!builtUrl.endsWith("?")) builtUrl += "?"
        args.forEach {
            builtUrl += "&${it.key}=${it.value}"
        }
    }
    if (attemptsLeft < 0) throw IOException()

    return try {
        val client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 300*1000
            }
        }
        val response: HttpResponse = runBlocking { client.get(builtUrl) }
        runBlocking { response.bodyAsText() }
    } catch (e: Exception) {
        e.printStackTrace()
        Thread.sleep(100)
        getPage(url, args, attemptsLeft - 1)
    }
}
fun getPageToResponse(url: String, args: Map<String, String> = mapOf(), attemptsLeft: Int = 50): HttpResponse {
    var builtUrl = url
    if (args.isNotEmpty()) {
        if (!builtUrl.endsWith("?")) builtUrl += "?"
        args.forEach {
            builtUrl += "&${it.key}=${it.value}"
        }
    }
    if (attemptsLeft < 0) throw IOException()

    return try {
        val client = HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 300*1000
            }
        }
        val response: HttpResponse = runBlocking { client.get(builtUrl) }
        response
    } catch (e: Exception) {
        e.printStackTrace()
        Thread.sleep(100)
        getPageToResponse(url, args, attemptsLeft - 1)
    }
}

fun getSchedulePage(args: Map<String, String> = mapOf()): String {
    var url = "$SCHEDULE_PAGE_URL?"
    args.forEach {
        url += "${it.key}=${it.value}&"
    }

    return getPage(url)
}

val TolerantJson = Json{ignoreUnknownKeys = true}
