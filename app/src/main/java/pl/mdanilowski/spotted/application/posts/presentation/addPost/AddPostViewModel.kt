package pl.mdanilowski.spotted.application.posts.presentation.addPost

import androidx.lifecycle.MutableLiveData
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler
import pl.mdanilowski.spotted.util.StorageUtil

class AddPostViewModel constructor(
    private val schedulers: BaseSchedulers,
    baseSchedulers: BaseSchedulers,
    errorHandler: ErrorHandler
) : BaseViewModel(baseSchedulers, errorHandler) {

    val message: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val tags: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    // TODO: Implement
}