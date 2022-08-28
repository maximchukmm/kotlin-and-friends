package edu.kotlinlang.algos

import io.ktor.util.date.getTimeMillis
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun runAllTests(suitName: String, numberOfTests: Int = 10000, maxSizeOfArray: Int = 100, ascSort: (Array<Int>) -> Unit) {
    runDemo(suitName) {
        runBasicTests(ascSort)
        runRandomTests(numberOfTests, maxSizeOfArray, ascSort)
    }
}

fun runBasicTests(ascSort: (Array<Int>) -> Unit) {
    runDemo("Already sorted array") {
        val array = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }

    runDemo("Reversely sorted array") {
        val array = arrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }

    runDemo("Unsorted array") {
        val array = arrayOf(4, 3, 1, 0, 7, 8, 6, 5, 9, 2)
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }

    runDemo("Empty array") {
        val array = emptyArray<Int>()
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }

    runDemo("Array with one element") {
        val array = arrayOf(0)
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }

    runDemo("array with 2 elements") {
        val array = arrayOf(1, 0)
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }

    runDemo("array with 3 elements") {
        val array = arrayOf(1, 0, 2)
        printlnIsSortedAsc(array)

        ascSort(array)
        printlnIsSortedAsc(array)
    }
}

fun runRandomTests(numberOfTests: Int, maxSizeOfArray: Int, ascSort: (Array<Int>) -> Unit) {
    runDemo("Random stress test") {
        val timeMillis = measureTimeMillis {
            val random = Random(getTimeMillis().toInt())

            for (i in 1..numberOfTests) {
                val size = random.nextInt(maxSizeOfArray) + 1

                val list: MutableList<Int> = ArrayList(size)
                for (j in 1..size) {
                    list += random.nextInt(100)
                }

                val arrayToSort = list.toTypedArray()

                ascSort(arrayToSort)

                if (!isSortedAsc(arrayToSort)) {
                    throw RuntimeException("Array ${arrayToSort.contentToString()} is not sorted in the ascending order!")
                }
            }
        }

        println("Random stress test for ascending sort is successful. It is finished in ${timeMillis / 1000.0} seconds")
    }
}