package pl.mdanilowski.spotted.application.posts.presentation.post

import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.application.posts.domain.model.Post
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler

class PostViewModel(baseSchedulers: BaseSchedulers, errorHandler: ErrorHandler) :
    BaseViewModel(baseSchedulers, errorHandler) {

    lateinit var post: Post

    // TODO: Implement
}