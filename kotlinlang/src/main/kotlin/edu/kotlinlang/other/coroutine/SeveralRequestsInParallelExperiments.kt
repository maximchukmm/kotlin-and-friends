package edu.kotlinlang.other.coroutine

import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private fun main() {
    val baseUrl = "https://traveltoc.ru/api/v1/activity/published/public/page"

    val httpClient = HttpClient()

    runBlocking {
        val time = measureTimeMillis {
            val responses = mutableListOf<Deferred<Unit>>()

            for (i in 0..9) {
                val response = async(Dispatchers.Default) {
                    val response = httpClient.post<HttpResponse>(baseUrl) {
                        headers { append(HttpHeaders.ContentType, "application/json;charset=utf-8") }
                        body = "{}"
                    }
                    println("Thread: ${Thread.currentThread()}, request number: $i, response: ${response.readText()}")
                }
                responses += response
            }

            responses.forEach { it.await() }
        }

        println("Time $time ms")
    }

    httpClient.close()
}