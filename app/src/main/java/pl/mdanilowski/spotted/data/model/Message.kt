package pl.mdanilowski.spotted.data.model

import java.io.Serializable
import java.util.*

data class Message(
    var message: String,
    var city: String,
    var date: Date?,
    var tags: List<String>?,
    var comments: ArrayList<Comment?>?
) : Serializable