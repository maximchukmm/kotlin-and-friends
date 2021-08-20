package edu.kotlinlang.other.coroutine

import edu.kotlinlang.log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()

    launch {
        repeat(6) {
            val number = channel.receive()
            log("Received number: $number")
        }
        log("The third coroutine is completed")
    }
    launch {
        channel.send(1)
        channel.send(2)
        channel.send(3)
        log("The first coroutine is completed")
    }
    launch {
        channel.send(4)
        channel.send(5)
        channel.send(6)
        log("The second coroutine is completed")
    }
}