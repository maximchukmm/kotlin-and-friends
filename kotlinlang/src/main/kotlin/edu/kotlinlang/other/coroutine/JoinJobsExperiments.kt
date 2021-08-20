package edu.kotlinlang.other.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun main() = runBlocking {
//    launch {
//        joinAll(
//            launch {
//                println("1 start")
//                delay(5000)
//                println("1 end")
//            }, launch {
//                println("2 start")
//                delay(4000)
//                println("2 end")
//            }, launch {
//                println("3 start")
//                delay(3000)
//                println("3 end")
//            }, launch {
//                println("4 start")
//                delay(2000)
//                println("4 end")
//            }, launch {
//                println("5 start")
//                delay(1000)
//                println("5 end")
//            }
//        )
//    }.join()

    launch {
        launch {
            println("1 start")
            delay(5000)
            println("1 end")
        }
        launch {
            println("2 start")
            delay(4000)
            println("2 end")
        }
        launch {
            println("3 start")
            delay(3000)
            println("3 end")
        }
        launch {
            println("4 start")
            delay(2000)
            println("4 end")
        }
        launch {
            println("5 start")
            delay(1000)
            println("5 end")
        }
    }.join()

    println("Completed")
}