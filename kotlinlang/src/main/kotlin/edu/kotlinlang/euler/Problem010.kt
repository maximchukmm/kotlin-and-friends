package edu.kotlinlang.euler

import kotlin.math.roundToLong
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    val n = 2_000_000
    val primes = mutableListOf<Long>(2, 3)

    var prime = 5L
    var isPrime = true

    val timeMillis = measureTimeMillis {
        while (prime < n) {
            val sqrt = sqrt(prime.toDouble()).roundToLong()

            for (p in primes) {
                if (prime % p == 0L) {
                    isPrime = false
                    break
                }
                if (p > sqrt) {
                    break
                }
            }

            if (isPrime) {
                primes += prime
            }

            isPrime = true
            prime += 2
        }
    }

    println("In ${timeMillis / 1000.0} seconds")

    println(primes.sum())
}