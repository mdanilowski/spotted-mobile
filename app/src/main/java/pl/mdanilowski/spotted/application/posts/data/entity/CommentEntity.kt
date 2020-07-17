package pl.mdanilowski.spotted.application.posts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class CommentEntity(
    @PrimaryKey(autoGenerate = true) var commentId: Long,
    val comment: String,
    val date: Date,
    val postId: Long
)