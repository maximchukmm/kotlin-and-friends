package edu.kotlinlang.other

import org.apache.pdfbox.Loader
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

private fun main() {
    val source = path("/home/mmaximchuk/inbox/codabra-001-unity-2d")

    val pagesNumber = Files.walk(source)
        .filter(::isPdfFile)
        .map(Path::toFile)
        .mapToInt(::countPdfFilePages)
        .sum()

    println(pagesNumber)
}

private fun countPdfFilePages(file: File): Int {
    val pdf = Loader.loadPDF(file)
    val pagesNumber = pdf.numberOfPages
    pdf.close()

    return pagesNumber
}

private fun isPdfFile(path: Path): Boolean {
    return path.toString().contains("pdf")
}

private fun path(absolutePath: String): Path = Paths.get(absolutePath)