package edu.kotlinlang.hackerrank.algorithms

fun main() {
    for (a in 1..9)
        for (b in a + 1..9)
            for (c in b + 1..9)
                if (a + b + c == 15)
                    println("$a $b $c")
}

private fun formingMagicSquare(s: Array<Array<Int>>): Int {


    TODO()
}