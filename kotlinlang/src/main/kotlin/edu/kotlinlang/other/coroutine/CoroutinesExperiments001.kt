package edu.kotlinlang.other.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun main() {

//    suspend fun task1() {
//        println("start task1 in Thread ${Thread.currentThread()}")
//        yield()
//        println("end task1 in Thread ${Thread.currentThread()}")
//    }
//
//    suspend fun task2() {
//        println("start task2 in Thread ${Thread.currentThread()}")
//        yield()
//        println("end task2 in Thread ${Thread.currentThread()}")
//    }
//
//    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use { context ->
//        println("start")
//        runBlocking {
//            println("starting in Thread ${Thread.currentThread()}")
//            withContext(Dispatchers.Default) { task1() }
//            launch { task2() }
//            println("ending in Thread ${Thread.currentThread()}")
//        }
//        println("done")
//    }

//    runBlocking {
//        val count: Deferred<Int> = async {
//            println("fetching in ${Thread.currentThread()}")
//            Runtime.getRuntime().availableProcessors()
//        }
//        println("Called the function in ${Thread.currentThread()}")
//        println("Number of cores is ${count.await()}")
//    }

    runBlocking<Unit> {
        val compute = Compute()

        launch(Dispatchers.Default) {
            compute.compute2(2)
        }
        launch(Dispatchers.Default) {
            compute.compute2(1)
        }
    }
}

class Compute {
    fun compute1(n: Long): Long = n * 2

    suspend fun compute2(n: Long): Long {
        val factor = 2
        println("$n received : Thread: ${Thread.currentThread()}")
        delay(n * 1000)
        val result = n * factor
        println("$n, returning $result: Thread: ${Thread.currentThread()}")
        return result
    }
}