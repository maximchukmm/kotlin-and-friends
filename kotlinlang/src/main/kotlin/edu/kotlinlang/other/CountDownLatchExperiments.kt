package edu.kotlinlang.other

import java.util.concurrent.CountDownLatch

fun main() {
    val integers = arrayOf(1, 2, 3, 4, 5)

    val countDownLatch = CountDownLatch(1)

    for (i in integers.indices) {
        Thread {
            val integer = integers[i]
            countDownLatch.await()
            println(integer)
        }.start()
    }

    countDownLatch.countDown()
}