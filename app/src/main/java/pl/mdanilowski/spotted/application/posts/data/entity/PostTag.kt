package pl.mdanilowski.spotted.application.posts.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["tag", "postId"])
data class PostTagCrossRef(
    val tag: String,
    val postId: Long
)