package edu.kotlinlang.other

fun main() {
    println("Collection")
    listOf(1, 2, 3)
        .map { add(it) }
        .first { it % 3 == 0 }
        .also { println(it) }

    println()

    println("Sequence")
    listOf(1, 2, 3)
        .asSequence()
        .map { add(it) }
        .first { it % 3 == 0 }
        .also { println(it) }

    println()

    println("Java Stream")
    listOf(1, 2, 3)
        .stream()
        .map { add(it) }
        .filter { it % 3 == 0 }
        .findFirst()
        .also { println(it) }
}

private fun add(number: Int): Int {
    println("$number + 1 = ${number + 1}")
    return number + 1;
}