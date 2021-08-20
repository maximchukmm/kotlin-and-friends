package edu.kotlinlang.other

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import kotlin.system.measureTimeMillis

suspend fun main() {
    val baseUrl = "http://127.0.0.1:8080/actuator/metrics/"

    val metrics = listOf(
        "hikaricp.connections",
        "hikaricp.connections.acquire",
        "hikaricp.connections.active",
        "hikaricp.connections.creation",
        "hikaricp.connections.idle",
        "hikaricp.connections.max",
        "hikaricp.connections.min",
        "hikaricp.connections.pending",
        "hikaricp.connections.timeout",
        "hikaricp.connections.usage",
        "http.server.requests",
        "jdbc.connections.active",
        "jdbc.connections.idle",
        "jdbc.connections.max",
        "jdbc.connections.min",
        "spring.data.repository.invocations",
        "tomcat.sessions.active.current",
        "tomcat.sessions.active.max",
        "tomcat.sessions.alive.max",
        "tomcat.sessions.created",
        "tomcat.sessions.expired",
        "tomcat.sessions.rejected"
    )

    val httpClient = HttpClient()
    val gson = GsonBuilder().setPrettyPrinting().create()

    val time = measureTimeMillis {
        for (metric in metrics) {
            println("GET /actuator/metrics/$metric")

            val response = httpClient.get<HttpResponse>(baseUrl + metric)

            val uglyJson = response.readText()
            val prettyJson = gson.toJson(JsonParser.parseString(uglyJson))

            println(prettyJson)

            println()
            println()
        }
    }

    println("Time $time ms")

    httpClient.close()
}