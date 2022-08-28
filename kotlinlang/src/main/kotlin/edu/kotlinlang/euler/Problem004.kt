package edu.kotlinlang.euler

fun main() {
    var maxP = 0

    for (i in 900..999) {
        for (j in i..999) {
            val p = i * j
            if (p > maxP && palindrome(p)) {
                println("$i * $j = $p")
                maxP = p
            }
        }
    }
}

private fun palindrome(n: Int): Boolean {
    val t = n.toString()
    val l = t.length

    for (i in 0..l / 2)
        if (t[i] != t[l - i - 1])
            return false

    return true
}
