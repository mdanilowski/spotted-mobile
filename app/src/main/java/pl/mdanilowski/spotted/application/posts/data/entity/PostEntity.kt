package pl.mdanilowski.spotted.application.posts.data.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "POST")
data class PostEntity(
    var message: String,
    var owningCityId: Long,
    var date: Date?
) {
    @PrimaryKey(autoGenerate = true)
    var postId: Long = 0
}

data class PostWithTagsAndComments(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "postId",
        entityColumn = "tag",
        associateBy = Junction(PostTagCrossRef::class)
    )
    val tags: List<TagEntity>,
    @Relation(
        parentColumn = "postId",
        entityColumn = "postId"
    )
    val comments: List<CommentEntity>
)
