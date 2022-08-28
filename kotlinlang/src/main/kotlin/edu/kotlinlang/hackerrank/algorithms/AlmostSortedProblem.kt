package edu.kotlinlang.hackerrank.algorithms

fun main() {
    almostSorted(arrayOf(6, 1, 2, 3, 4, 5, 0, 7))
    almostSorted(arrayOf(6, 5, 4, 3, 2, 1, 0, 7, 8, 9))
    almostSorted(arrayOf(1, 2, 4, 3, 5, 6))
    almostSorted(arrayOf(1, 2, 4, 3))
    almostSorted(arrayOf(1, 2, 3, 4))
}

// 6 1 2 3 4 5 0 7
// 6 5 4 3 2 1 0 7 8 9
// 1 2 4 3 5 6
// 1 2 4 3
fun almostSorted(arr: Array<Int>) {
    if (arr.size == 2) {
        println("yes")
        if (arr[0] > arr[1]) {
            println("swap 1 2")
        }
        return
    }

    val n = arr.size
    var l = -1
    var r = -1
    var i = 0
    var desc = false

    while (i < n - 1) {
        if (arr[i] > arr[i + 1] && l == -1) {

        }
    }

    println("$l $r")
}