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
    fun `hello, Hibernate!`() {
        val greetings = "Hello, Hibernate!"

        doInTransaction { session ->
            run {
                session?.persist(Hello(null, greetings))
            }
        }

        doInTransaction { session ->
            run {
                val selectAllQuery = session?.createQuery("select h from Hello h", Hello::class.java)

                val foundEntities = selectAllQuery?.resultList

                assertTrue { foundEntities?.size == 1 }

                assertEquals(greetings, foundEntities?.get(0)?.greetings)
            }
        }
    }

    @Entity(name = "Hello")
    @Table(name = "hello")
    private class Hello(
            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            var id: Long?,

            var greetings: String?
    )
}