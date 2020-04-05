package edu.springbootbasics.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "author")
class Author : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "full_name")
    var fullName: String? = null

    override fun toString(): String {
        return "Author(id=$id, fullName=$fullName)"
    }
}