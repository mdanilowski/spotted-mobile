package pl.mdanilowski.spotted.application.posts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagEntity(
   @PrimaryKey var tag: String
)