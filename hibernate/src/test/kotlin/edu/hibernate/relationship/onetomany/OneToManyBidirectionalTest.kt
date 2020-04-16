package edu.hibernate.relationship.onetomany

import edu.hibernate.HibernateBaseTest
import org.hamcrest.Matchers
import org.junit.Assert.assertThat
import org.junit.Test
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

class OneToManyBidirectionalTest : HibernateBaseTest() {
    override fun entities(): Array<Class<*>> = arrayOf(Post::class.java, PostComment::class.java)

    @Test
    fun `when parent entity is persisted with cascading all operations then children entities are persisted too`() {
        doInTransaction { session ->
            val post = Post(1L, "The quarantine is finally ended")

            val comment1 = PostComment(1L, "Let's party!", post)
            val comment2 = PostComment(2L, "Let's work!", post)

            post.addComment(comment1)
            post.addComment(comment2)

            session?.persist(post)
        }

        doInTransaction { session ->
            val foundPost = session?.find(Post::class.java, 1L)

            assertThat(foundPost?.comments, Matchers.hasSize(2))
        }
    }

    @Entity(name = "Post")
    @Table(name = "post")
    private data class Post(
            @Id
            var id: Long?,

            @Column(nullable = false, length = 100)
            var title: String?,

            @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL])
            var comments: MutableList<PostComment> = ArrayList()
    ) {
        fun addComment(comment: PostComment) {
            comments.add(comment)
        }
    }

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