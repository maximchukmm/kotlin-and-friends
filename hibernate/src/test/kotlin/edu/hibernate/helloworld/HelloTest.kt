package edu.hibernate.helloworld

import edu.hibernate.HibernateBaseTest
import org.junit.Test
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HelloTest : HibernateBaseTest() {

    override fun entities(): Array<Class<*>> = arrayOf(Hello::class.java)

    @Test
    fun `hello Hibernate!`() {
        val greetings = "Hello, Hibernate!"

        doInTransaction { session ->
            run {
                session?.persist(Hello(greetings))
            }
        }

        doInTransaction { session ->
            run {
                val selectAllQuery = session?.createQuery("select h from Hello h", Hello::class.java)

                val foundEntities = selectAllQuery?.resultList

                log.info("Found entities: {}", foundEntities.toString())

                assertTrue { foundEntities?.size == 1 }

                assertEquals(greetings, foundEntities?.get(0)?.greetings)

            }
        }
    }

    @Entity(name = "Hello")
    @Table(name = "hello")
    private class Hello(greetings: String?) {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null

        var greetings: String? = greetings

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Hello

            if (id != other.id) return false
            if (greetings != other.greetings) return false

            return true
        }

        override fun hashCode(): Int {
            var result = id?.hashCode() ?: 0
            result = 31 * result + (greetings?.hashCode() ?: 0)
            return result
        }

        override fun toString(): String {
            return "Hello(id=$id, greetings=$greetings)"
        }
    }
}