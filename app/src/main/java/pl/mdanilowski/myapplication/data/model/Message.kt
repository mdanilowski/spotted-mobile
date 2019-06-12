package pl.mdanilowski.myapplication.data.model

import java.util.*

data class Message(
    var message: String,
    var city: String,
    var date: Date?,
    var tags: ArrayList<String>?,
    var comments: ArrayList<Comment?>?
)