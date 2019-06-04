package pl.starter.android.data

import java.util.*

data class Message(var _id: String,
                   var message: String,
                   var city: String,
                   var date: Date?,
                   var tags: ArrayList<String>?,
                   var comments: ArrayList<Comment?>?)