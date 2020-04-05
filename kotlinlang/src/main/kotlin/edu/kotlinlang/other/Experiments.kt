package edu.kotlinlang.other

fun main() {
    printVarargNumbers(1, 2, 3)
    val arrayOfNumbers = intArrayOf(4, 5, 6)
    // printVarargNumbers(arrayOfNumbers) //doesn't compile
    printVarargNumbers(*arrayOfNumbers)
}

fun printVarargNumbers(vararg numbers: Int) {
    numbers.forEach { print(" $it") }
    println()
}