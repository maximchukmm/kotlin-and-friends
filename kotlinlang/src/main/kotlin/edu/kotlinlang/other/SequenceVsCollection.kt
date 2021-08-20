package edu.kotlinlang.other

fun main() {
    println("Collection")
    listOf(1, 2, 3)
        .map { add(it) }
        .first { it % 3 == 0 }
        .also { println(it) }

    println()

    println("Sequence")
    sequenceOf(1, 2, 3)
        .map { add(it) }
        .first { it % 3 == 0 }
        .also { println(it) }
}

private fun add(number: Int): Int {
    println("$number + 1 = ${number + 1}")
    return number + 1;
}