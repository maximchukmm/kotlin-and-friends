package edu.kotlinlang.algos

fun main() {
    runAllTests(suitName = "Bubble sort", ascSort = ::bubbleSortAsc)
}

// todom calc bubble sort complexity
private fun <T : Comparable<T>> bubbleSortAsc(a: Array<T>) {
    var n = a.size
    var swapped = false

    do {
        for (i in 0..n - 2) {
            if (a[i] > a[i + 1]) {
                val t = a[i]
                a[i] = a[i + 1]
                a[i + 1] = t
                swapped = true
            }
        }

        n--
    } while (swapped && n > 1)
}