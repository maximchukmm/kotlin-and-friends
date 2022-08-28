package edu.kotlinlang.euler

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    val primes = ArrayList<Long>(1024)
    primes += 2
    primes += 3
    primes += 5
    primes += 7

    measureTimeMillis {
//        val n = 10000.0
        val n = Long.MAX_VALUE.toDouble()

        for (i in 9..sqrt(n).toLong() step 2) {
            if (primeNumber(primes, i)) {
                primes += i
            }
            if (primes.size == 10001) {
                break
            }
        }
    }

    println(primes.size)
//    println(primes)
    println(primes.last())
}

private fun primeNumber(primes: List<Long>, numberToCheck: Long): Boolean {
    for (prime in primes) {
        if (2 * prime - numberToCheck >= 0L) break
        if (numberToCheck % prime == 0L) return false
    }
    return true
}