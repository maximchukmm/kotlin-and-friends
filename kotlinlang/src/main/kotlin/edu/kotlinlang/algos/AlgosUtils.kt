package edu.kotlinlang.algos

fun runDemo(demoDescription: String, block: () -> Unit) {
    println("============ $demoDescription: Start ============")
    block()
    println("============ $demoDescription: End   ============\n")
}

fun <T : Comparable<T>> isSortedAsc(array: Array<T>): Boolean {
    for (i in 0..array.size - 2)
        if (array[i] > array[i + 1]) return false

    return true
}

fun <T : Comparable<T>> printlnIsSortedAsc(array: Array<T>) {
    val sorted = isSortedAsc(array)

    if (array.size < 20) println("Sorted asc: $sorted\t-> ${array.contentToString()}")
    else println("Sorted asc: $sorted\t-> ${array.copyOfRange(0, 20).contentToString()}...")

}