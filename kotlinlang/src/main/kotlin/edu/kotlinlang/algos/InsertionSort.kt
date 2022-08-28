package edu.kotlinlang.algos

fun main() {
    runAllTests(suitName = "Naive insertion sort", ascSort = ::naiveInsertionSortAsc)
    runAllTests(suitName = "Insertion sort", ascSort = ::insertionSortAsc)
}

// todom calc insertion sort complexity
private fun <T : Comparable<T>> insertionSortAsc(a: Array<T>) {
    for (i in 1..a.size - 1) {
        val key = a[i]

        var j = i - 1
        while (j >= 0 && a[j] > key) {
            a[j + 1] = a[j]
            j--
        }

        a[j + 1] = key
    }
}

private fun <T: Comparable<T>> naiveInsertionSortAsc(a: Array<T>) {
    for (i in 1..a.size - 1) {
        var insertIndex = i

        for (j in 0..i - 1) {
            if (a[i] < a[j]) {
                insertIndex = j
                break
            }
        }

        val insertValue = a[i]
        for (k in i downTo insertIndex + 1) {
            a[k] = a[k - 1]
        }
        a[insertIndex] = insertValue
    }
}
