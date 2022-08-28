package edu.kotlinlang.algos

fun main() {
    runAllTests(suitName = "Selection sort", ascSort = ::selectionSortAsc)
}

// todom calc selection sort complexity
private fun <T : Comparable<T>> selectionSortAsc(a: Array<T>) {
    for (i in 0..a.size - 2) {
        var min = a[i]
        var minInd = i

        for (j in i + 1..a.size - 1) {
            if (a[j] < min) {
                min = a[j]
                minInd = j
            }
        }

        val cur = a[i]
        a[i] = min
        a[minInd] = cur
    }
}