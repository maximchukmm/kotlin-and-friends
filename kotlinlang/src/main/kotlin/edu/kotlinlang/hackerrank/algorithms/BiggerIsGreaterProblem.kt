package edu.kotlinlang.hackerrank.algorithms

fun main() {
    listOf(
        "ab",
        "bb",
        "hefg",
        "dhck",
        "dkhc",
        "lmno",
        "dcba",
        "dcbb",
        "abdc",
        "abcd",
        "fedcbabcd"
    )
        .forEach { println(biggerIsGreater(it)) }
}

private fun biggerIsGreater(w: String): String {
    val c = mutableListOf<Char>()
    var idxSwap = -1

    for (i in w.length - 1 downTo 1) {
        if (w[i] > w[i - 1]) {
            idxSwap = i - 1
            c += w[i]
            break
        }
        c += w[i]
    }

    if (idxSwap == -1) return "no answer"

    c.sort()
    val idxClosestToSwap = ceilTo(c, w[idxSwap])

    val swapped = c[idxClosestToSwap]
    c[idxClosestToSwap] = w[idxSwap]

    return w.substring(0, idxSwap) + swapped + c.joinToString(separator = "")
}

private fun ceilTo(chars: List<Char>, c: Char): Int {
    for (i in 0..chars.size - 1) {
        if (c < chars[i]) return i
    }

    return -1
}

