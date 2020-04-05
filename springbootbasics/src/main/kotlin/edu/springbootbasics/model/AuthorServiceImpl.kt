package edu.springbootbasics.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl @Autowired constructor(var authorRepository: AuthorRepository) : AuthorService {

    override fun printAuthorByName(fullName: String) {
        val author = authorRepository.findByFullName(fullName);
        println("Found $author by with full name: $fullName")
    }
}