package edu.kotlinlang.euler

fun main() {
    val n = 1_000_000

    var maxMult = emptyList<Int>()

    for (i in 1..n) {
        val mult = mutableListOf(1)

        for (j in 2..(1 + i / 2)) {
            if (i % j == 0) mult += j
        }

        if (mult.size > maxMult.size) {
            maxMult = mult
            println("$i: $maxMult")
        }
    }

    println()
    println(maxMult)
}