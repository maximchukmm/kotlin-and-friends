package edu.kotlinlang.other

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.isDirectory

private fun main() {
    val source = path("/home/mmaximchuk/Documents/now/pdfs/input")
    val destination = path("/home/mmaximchuk/Documents/now/pdfs/output")
    val ext = "pdf"

    Files.walk(source)
        .filter { !it.isDirectory() && it.toString().contains(ext) }
        .sorted()
        .toList()
        .forEachIndexed { i, p -> copy(input = p, fileNumber = i, destination = destination, ext = ext) }
}

private fun path(absolutePath: String): Path = Paths.get(absolutePath)

private fun copy(input: Path, fileNumber: Int, destination: Path, ext: String) {
    val target = File(buildString {
        append(destination)
        append("/")
        append(fileNumber.toString().padStart(4, '0'))
        append(".")
        append(ext)
    })

    input
        .toFile()
        .copyTo(target)
}