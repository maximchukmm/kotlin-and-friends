package edu.kotlinlang.other

import com.google.gson.GsonBuilder

/*
[
    {
        id: "0",
        name: "desc 0",
        children: [
            {
                id: "0.1",
                name: "desc 0.1",
                children: [
                    {
                        id: "0.1.1",
                        name: "desc 0.1.1",
                        children: []
                    },
                    {
                        id: "0.1.2",
                        name: "desc 0.1.2",
                        children: []
                    },
                    {
                        id: "0.1.3",
                        name: "desc 0.1.3",
                        children: []
                    },
                ]
            },
            {
                id: "0.2",
                name: "desc 0.2",
                children: []
            },
            {
                id: "0.3",
                name: "desc 0.3",
                children: []
            }
        ]
    },
    {
        id: "1",
        name: "desc 1",
        children: []
    }
]
 */

// todom - bad equals, toString, hashCode
data class Item(val id: String, val name: String, val items: MutableList<Item> = mutableListOf()) {
    fun addChild(item: Item) {
        items += item
    }

    override fun toString(): String {
        return "$id $items"
    }
}

fun main() {
    val csv = arrayOf(
        arrayOf("0", "desc 0"),
        arrayOf("0.1", "desc 0.1"),
        arrayOf("0.1.1", "desc 0.1.1"),
        arrayOf("0.1.2", "desc 0.1.2"),
        arrayOf("0.1.3", "desc 0.1.3"),
        arrayOf("0.1.3.1", "desc 0.1.3.1"),
        arrayOf("0.1.3.2", "desc 0.1.3.2"),
        arrayOf("0.2", "desc 0.2"),
        arrayOf("0.3", "desc 0.3"),
        arrayOf("1", "desc 1"),
        arrayOf("2", "desc 2"),
        arrayOf("3", "desc 3"),
        arrayOf("3.1", "desc 3.1"),
    )

    val stack = ArrayDeque<Item>()

    for (i in csv) {
        val item = Item(i[0], i[1])

        if (stack.isEmpty()) {
            stack.addFirst(item)
        } else if (isChild(stack.first(), item)) {
            stack.addFirst(item)
        } else if (isSameLevel(stack.first(), item)) {
            if (stack.size == 1) {
                stack.addFirst(item)
            } else {
                val curr = stack.removeFirst()
                val prev = stack.removeFirst()

                if (isChild(prev, curr)) {
                    prev.addChild(curr)
                    stack.addFirst(prev)
                } else {
                    stack.addFirst(prev)
                    stack.addFirst(curr)
                }
                stack.addFirst(item)
            }
        } else {
            collapse(stack)
            stack.addFirst(item)
        }
    }

    collapse(stack)

    val items = mutableListOf<Item>()
    while (!stack.isEmpty()) {
        items += stack.removeLast()
    }

    val gson = GsonBuilder().setPrettyPrinting().create()
    println(gson.toJson(items))
}

fun isChild(prev: Item, curr: Item): Boolean {
    val prevLevels = prev.id.split(".")
    val currLevels = curr.id.split(".")

    return currLevels.size - prevLevels.size == 1 && prevLevels == currLevels.subList(0, currLevels.size - 1)
}

fun isSameLevel(prev: Item, curr: Item): Boolean {
    val prevLevels = prev.id.split(".")
    val currLevels = curr.id.split(".")

    return currLevels.size == prevLevels.size &&
            prevLevels.subList(0, prevLevels.size - 1) == currLevels.subList(0, currLevels.size - 1)
}

fun collapse(stack: ArrayDeque<Item>) {
    if (stack.size < 2) return

    var curr = stack.removeFirst()
    var prev = stack.removeFirst()

    while (isChild(prev, curr)) {
        prev.addChild(curr)
        stack.addFirst(prev)

        if (stack.size < 2) {
            break
        }

        curr = stack.removeFirst()
        prev = stack.removeFirst()
    }
}
