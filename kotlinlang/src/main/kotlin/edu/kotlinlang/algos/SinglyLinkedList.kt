package edu.kotlinlang.algos

import java.util.StringJoiner

fun main() {
    runDemo("Empty list - its size and emptiness") {
        val list = SinglyLinkedList<Int>()

        println(list.size())
        println("is empty: ${list.empty()}")
        list.println()
    }

    runDemo("add last") {
        val list = SinglyLinkedList<Int>()
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.addLast(4)
        list.addLast(5)

        println(list.size())
        list.println()
    }

    runDemo("add last then clear list with remove last") {
        val list = SinglyLinkedList<Int>()
        list.addLast(0)
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.addLast(4)

        list.printlnWithSize()

        val size = list.size()
        for (i in 1..size) {
            list.removeLast()
            list.printlnWithSize()
        }
    }

    runDemo("add first then clear list with remove last") {
        val list = SinglyLinkedList<Int>()
        list.addFirst(0)
        list.addFirst(1)
        list.addFirst(2)
        list.addFirst(3)
        list.addFirst(4)

        println("is empty: ${list.empty()}")
        list.printlnWithSize()

        val size = list.size()
        for (i in 1..size) {
            list.removeLast()
            list.printlnWithSize()
        }

        println("is empty: ${list.empty()}")
    }

    runDemo("add last then clear with remove first") {
        val list = SinglyLinkedList<Int>()
        list.addLast(0)
        list.addLast(1)
        list.addLast(2)
        list.addLast(3)
        list.addLast(4)

        list.printlnWithSize()

        val size = list.size()
        for (i in 1..size) {
            list.removeFirst()
            list.printlnWithSize()
        }
    }

    runDemo("add first then clear with remove first") {
        val list = SinglyLinkedList<Int>()
        list.addFirst(0)
        list.addFirst(1)
        list.addFirst(2)
        list.addFirst(3)
        list.addFirst(4)

        println("is empty: ${list.empty()}")
        list.printlnWithSize()

        val size = list.size()
        for (i in 1..size) {
            list.removeFirst()
            list.printlnWithSize()
        }

        println("is empty: ${list.empty()}")
    }
}

/*
[] - set by index
+addFirst
+addLast
insert - add new after index - throw an exception if out of list's bound
remove by index - throw an exception if out of list's bound
peekFirst - retrieves, but does not remove - throw an exception if empty list
peekLast - retrieves, but does not remove - throw an exception if empty list
pollFirst - retrieves and removes - throw an exception if empty list
pollLast - retrieves and removes - throw an exception if empty list
+removeFirst
+removeLast
+size
+isEmpty
contains
 */

class SinglyLinkedList<T> {
    private class Node<E>(var value: E, var next: Node<E>?)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun addFirst(value: T) {
        if (addHeadIfNull(value)) return
        if (head === tail) {
            head = Node(value, tail)
            return
        }

        head = Node(value, head)
    }

    fun addLast(value: T) {
        if (addHeadIfNull(value)) return
        if (head === tail) {
            tail = Node(value, null)
            head!!.next = tail
            return
        }

        tail!!.next = Node(value, null)
        tail = tail!!.next
    }

    fun removeFirst() {
        if (removeHeadIfOnlyHeadOrNull()) return

        head = head!!.next
    }

    fun removeLast() {
        if (removeHeadIfOnlyHeadOrNull()) return

        tail = beforeTail()
        tail!!.next = null
    }

    fun size(): Int {
        var size = 0

        var curr = head

        while (curr != null) {
            curr = curr.next
            size++
        }

        return size
    }

    fun empty() = size() == 0

    fun println() {
        println(this)
    }

    fun printlnWithSize() {
        println("${size()}: $this")
    }

    private fun addHeadIfNull(value: T): Boolean =
        when (head) {
            null -> {
                head = Node(value, null)
                tail = head
                true
            }
            else -> false
        }

    private fun removeHeadIfOnlyHeadOrNull(): Boolean =
        when (head) {
            null -> true
            tail -> {
                head = null
                tail = null
                true
            }
            else -> false
        }

    private fun beforeTail(): Node<T> {
        var beforeTail = head

        while (beforeTail!!.next != tail) {
            beforeTail = beforeTail.next
        }

        return beforeTail
    }

    override fun toString(): String {
        var curr = head
        val joiner = StringJoiner(", ", "[", "]")

        while (curr != null) {
            joiner.add(curr.value.toString())
            curr = curr.next
        }

        return joiner.toString()
    }
}