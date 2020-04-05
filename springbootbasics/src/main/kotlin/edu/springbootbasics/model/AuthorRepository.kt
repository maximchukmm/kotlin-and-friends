package edu.springbootbasics.model

import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<Author, Long> {
    fun findByFullName(fullName: String): Author
}