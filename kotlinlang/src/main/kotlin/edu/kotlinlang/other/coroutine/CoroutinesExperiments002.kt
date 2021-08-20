package edu.kotlinlang.other.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

private fun main() {
    println("Hello!")

    println("After main: ${Thread.currentThread()}")

    thread {
        println("After thread: ${Thread.currentThread()}")
        runBlocking {
            println("After runBlocking: ${Thread.currentThread()}")
            launch {
                println("After launch: ${Thread.currentThread()}")
                delay(5000)
                println("???")
            }
            val asyncResult = async {
                println("After async: ${Thread.currentThread()}")
                delay(8000)
                "I am async!"
            }
            println("Async result: ${asyncResult.await()}")
        }
    }

    Thread.sleep(2000)

    println("Bye!")

    Thread.sleep(4000)
}