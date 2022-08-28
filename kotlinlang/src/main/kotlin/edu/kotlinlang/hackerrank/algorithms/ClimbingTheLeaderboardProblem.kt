package edu.kotlinlang.hackerrank.algorithms

fun main() {
    val ranked = arrayOf(100, 90, 90, 80, 75, 60, 60)
    val player = arrayOf(50, 65, 77, 90, 102)

    println(climbingLeaderboard(ranked, player).contentToString())
}

private fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
    val ranks = mutableListOf<Int>()
    var curRank = minRank(ranked)
    var r = ranked.size - 1
    var p = 0

    while (p < player.size) {
        while (r > 0 && ranked[r] == ranked[r - 1]) {
            r--
        }

        if (player[p] < ranked[r]) {
            ranks += curRank + 1
            p++
        } else if (player[p] == ranked[r]) {
            ranks += curRank
            p++
        } else if (r == 0) {
            ranks += curRank
            p++
        } else {
            while (r > 0 && player[p] > ranked[r]) {
                r--
                while (r > 0 && ranked[r] == ranked[r - 1]) {
                    r--
                }
                curRank--
            }
        }
    }

    return ranks.toTypedArray()
}

private fun minRank(ranked: Array<Int>): Int {
    var minRank = 1

    for (i in 0..ranked.size - 2) {
        if (ranked[i] > ranked[i + 1]) {
            minRank++
        }
    }

    return minRank
}