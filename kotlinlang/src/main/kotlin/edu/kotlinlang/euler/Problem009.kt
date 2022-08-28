package edu.kotlinlang.euler

fun main() {
    var found = false

    for (a in 1..1000) {
        if (found) break

        for (b in 1..1000) {
            if (found) break

            val a2 = a * a
            val b2 = b * b

            for (c in 1..1000) {
                if (a + b + c == 1000) {
                    if (a2 + b2 == c * c) {
                        println("$a $b $c")
                        found = true
                        break
                    }
                }
            }
        }
    }

    println(200 * 375 * 425)
}