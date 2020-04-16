package edu.hibernate.relationship.onetomany

import edu.hibernate.HibernateBaseTest
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

class OneToManyUnidirectionalTest : HibernateBaseTest() {

    override fun entities(): Array<Class<*>> = arrayOf(Post::class.java, PostComment::class.java)

    @Test
    fun `when child entity is retrieved then parent entity is also retrieved (eagerly by default)`() {
        doInTransaction { session ->
            val post = Post(1L, "First post")
            val comment = PostComment(1L, "One to many relationship test", post)

            session?.persist(post)
            session?.persist(comment)
        }

        doInTransaction { session ->
            val foundComment = session?.find(PostComment::class.java, 1L)

            val expectedComment = PostComment(1L, "One to many relationship test", Post(1L, "First post"))

            assertThat(foundComment, `is`(equalTo(expectedComment)))
        }
    }

    @Entity(name = "Post")
    @Table(name = "post")
    private data class Post(
            @Id
            var id: Long?,

            @Column(nullable = false, length = 100)
            var title: String?
    )

    @Entity(name = "PostComment")
    @Table(name = "post_comment")
    private data class PostComment(
            @Id
            var id: Long?,

            @Column(nullable = false)
            var text: String?,

            @ManyToOne
            @JoinColumn(name = "post_id", nullable = false, referencedColumnName = "id", foreignKey = ForeignKey(name = "fk_post_id"))
            var post: Post?
    )
}