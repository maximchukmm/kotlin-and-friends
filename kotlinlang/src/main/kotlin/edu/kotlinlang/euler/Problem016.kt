package edu.kotlinlang.euler

import java.math.BigInteger
import kotlin.system.measureNanoTime

fun main() {
    val power = 1000

    val n = 15000
    val a = IntArray(n)
    a[0] = 2

    var d = 1

    val timeNanosNaiveApproach = measureNanoTime {
        for (j in 2..power) {
            var i = 0
            var r = 0
            var v: Int

            for (k in 1..d) {
                if (a[i] > 4) {
                    v = (a[i] shl 1) + r - 10
                    r = 1
                } else {
                    v = (a[i] shl 1) + r
                    r = 0
                }

                a[i++] = v
            }

            if (r > 0) {
                a[i] += r
                d++
            }
        }
    }

    var toPrint = false
    for (i in a.size - 1 downTo 0) {
        if (toPrint) {
            print(a[i])
            continue
        }
        if (a[i] != 0) {
            toPrint = true
            print(a[i])
        }
    }

    println()
    println("Naive approach in $timeNanosNaiveApproach nanos")
    println(a.sum())

    println()

    var bigInteger: BigInteger
    val timeNanosBigIntegerApproach = measureNanoTime {
        bigInteger = BigInteger.valueOf(2).pow(power)
    }
    println(bigInteger)
    println("BigInteger approach in $timeNanosBigIntegerApproach nanos")

    BigInteger
        .valueOf(2)
        .pow(1000)
        .toString()
        .toCharArray()
        .sumOf { it.digitToInt() }
        .also { println(it) }
}