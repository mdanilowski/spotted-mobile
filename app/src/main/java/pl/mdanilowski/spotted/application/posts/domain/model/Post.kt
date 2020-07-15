package pl.mdanilowski.spotted.application.posts.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Post(
    val message: String,
    val city: String,
    val date: Date?,
    val tags: List<String>?,
    val comments: List<Comment?>?
) : Parcelable
