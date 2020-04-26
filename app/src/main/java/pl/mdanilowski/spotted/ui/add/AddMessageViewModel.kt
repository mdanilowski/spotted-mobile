package pl.mdanilowski.spotted.ui.add

import androidx.databinding.ObservableField
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.data.ApiService
import pl.mdanilowski.spotted.data.model.Message
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.Constants
import pl.mdanilowski.spotted.util.ErrorHandler
import pl.mdanilowski.spotted.util.StorageUtil

class AddMessageViewModel constructor(
    private val apiService: ApiService,
    private val schedulers: BaseSchedulers,
    private val storageUtil: StorageUtil,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    var message: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)
    var tags: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)

//    fun addMessage() {
//        disposable.add(
//            apiService.addMessage(
//                Message(
//                    message.get()!!,
//                    storageUtil.getCityName()!!,
//                    null,
//                    tags.get()!!.split(" "),
//                    null
//                )
//            )
//                .subscribeOn(schedulers.io())
//                .observeOn(schedulers.main())
//                .subscribe(
//                    { view?.showToastMessage("Added successfully") },
//                    { e -> view?.showToastMessage("Error: " + e.message) })
//        )
//    }
}