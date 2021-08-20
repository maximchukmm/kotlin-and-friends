package edu.kotlinlang

import java.time.LocalDateTime
import java.time.ZoneId

fun log(message: String) {
    println("[${Thread.currentThread().name}][${LocalDateTime.now(ZoneId.of("UTC"))}] $message")
}