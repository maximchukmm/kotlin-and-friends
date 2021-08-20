package edu.hibernate.relationship.onetoone

import edu.hibernate.HibernateBaseTest
import org.junit.Ignore
import org.junit.Test
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import kotlin.test.assertEquals

class OneToOneWithJoinColumnAsSimpleIdTest : HibernateBaseTest() {
    override fun entities(): Array<Class<*>> = arrayOf(Post::class.java, PostComment::class.java)

    @Test
    @Ignore
    fun `when join column is id then able to create proper table for child entity and retrieve that child entity`() {
        doInTransaction { session ->
            checkNotNull(session)

            val post = Post(1, "Post")
            session.persist(post)
            session.persist(PostComment(1, "Post comment", post.id))
        }

        doInTransaction { session ->
            checkNotNull(session)

            val postComment = session.find(PostComment::class.java, 1)

            assertEquals(1, postComment.postId)
            assertEquals("Post comment", postComment.text)
        }
    }

    @Entity(name = "Post")
    @Table(name = "post")
    private data class Post(
            @Id
            val id: Int,

            @Column(nullable = false)
            val title: String
    )

    @Entity(name = "PostComment")
    @Table(name = "post_comment")
    private data class PostComment(
            @Id
            val id: Int,

            @Column(nullable = false)
            val text: String,

            @OneToOne(targetEntity = Post::class)
            @JoinColumn(
                    name = "post_id",
                    nullable = false,
                    referencedColumnName = "id",
                    foreignKey = ForeignKey(name = "fk_post_id"),
                    unique = true)
            val postId: Int
    )
}