package edu.kotlinlang.googleandroidcourse

private fun main() {
    printInfoAboutDwelling(SquareCabin(6))
    printInfoAboutDwelling(RoundHut(4))
    printInfoAboutDwelling(RoundTower(4))
}

private fun printInfoAboutDwelling(dwelling: Dwelling) {
    with(dwelling) {
        println("\n${dwelling.javaClass.simpleName}\n============")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
    }
}

// todom: rewrite with sealed classes

abstract class Dwelling(private var residents: Int) {

    abstract val buildingMaterial: String
    abstract val capacity: Int

    fun hasRoom(): Boolean = residents < capacity
}

class SquareCabin(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6
}

open class RoundHut(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4
}

class RoundTower(residents: Int, val floors: Int = 2) : RoundHut(residents) {
    override val buildingMaterial = "Stone"
    override val capacity = super.capacity * floors
}