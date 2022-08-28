package edu.kotlinlang.euler

import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        val primes = setOf(*sieve(1_000_000).toTypedArray())

        val circularPrimes = mutableSetOf(2, 3, 5)

        for (prime in primes) {
            if (circularPrimes.contains(prime)) continue

            val circularNumbers = circularNumbers(prime)
            if (circularNumbers.isEmpty()) continue
            var isCircularPrime = true

            for (circular in circularNumbers) {
                if (!primes.contains(circular)) {
                    isCircularPrime = false
                    break
                }
            }

            if (isCircularPrime) circularPrimes += circularNumbers
        }
    }.also { println("Finished in $it millis") }
}

private fun circularNumbers(number: Int): List<Int> {
    val nonPrimeEndings = setOf(0, 2, 4, 5, 6, 8)

    val d = mutableListOf<Int>()
    var n = number
    var r: Int

    while (n != 0) {
        r = n % 10
        if (r in nonPrimeEndings) return emptyList()

        d += r
        n /= 10
    }

    val circularNumbers = mutableListOf<Int>()

    for (i in 0 until d.size) {
        var degree = 1
        var circular = 0
        for (digit in d) {
            circular += digit * degree
            degree *= 10
        }

        val l = d.last()
        for (j in d.size - 1 downTo 1) {
            d[j] = d[j - 1]
        }
        d[0] = l

        circularNumbers += circular
    }

    return circularNumbers
}

private fun sieve(until: Int): List<Int> {
    val primes = mutableListOf(2, 3)

    var sqrtP: Int
    var isPrime: Boolean

    for (p in 5..until step 2) {
        sqrtP = sqrt(p.toFloat()).roundToInt()
        isPrime = true

        for (prime in primes) {
            if (p % prime == 0) {
                isPrime = false
                break
            }
            if (prime > sqrtP) {
                break
            }
        }

        if (isPrime) {
            primes += p
        }
    }

    return primes
}