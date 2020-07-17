package pl.mdanilowski.spotted.application.posts.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Comment(val comment: String, val date: Date?) : Parcelable