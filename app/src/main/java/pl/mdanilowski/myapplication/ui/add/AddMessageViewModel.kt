package pl.mdanilowski.myapplication.ui.add

import androidx.databinding.ObservableField
import pl.mdanilowski.myapplication.base.BaseView
import pl.mdanilowski.myapplication.base.BaseViewModel
import pl.mdanilowski.myapplication.data.ApiService
import pl.mdanilowski.myapplication.data.model.Message
import pl.mdanilowski.myapplication.util.BaseSchedulers
import pl.mdanilowski.myapplication.util.Constants
import pl.mdanilowski.myapplication.util.StorageUtil
import javax.inject.Inject

interface AddMessageView : BaseView {
    fun setupClickListeners()
}

class AddMessageViewModel @Inject constructor(
    private val apiService: ApiService,
    private val schedulers: BaseSchedulers,
    private val storageUtil: StorageUtil
) : BaseViewModel<AddMessageView>() {

    var message: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)
    var tags: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)

    fun addMessage() {
        disposable.add(
            apiService.addMessage(
                Message(
                    message.get()!!,
                    storageUtil.getCityName()!!,
                    null,
                    tags.get()!!.split(" "),
                    null
                )
            )
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe(
                    { view?.showToastMessage("Added successfully") },
                    { e -> view?.showToastMessage("Error: " + e.message) })
        )
    }
}