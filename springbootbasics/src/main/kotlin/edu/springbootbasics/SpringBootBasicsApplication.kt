package edu.springbootbasics

import edu.springbootbasics.model.AuthorService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class SpringBootBasicsApplication

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = runApplication<SpringBootBasicsApplication>(*args)

    val authorService: AuthorService = applicationContext.getBean(AuthorService::class.java)

    authorService.printAuthorByName("Ivanov I.")
}