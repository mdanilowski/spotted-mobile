package pl.mdanilowski.spotted.ui.dashboard

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.data.ApiService
import pl.mdanilowski.spotted.data.model.Message
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler
import pl.mdanilowski.spotted.util.StorageUtil

class DashboardViewModel constructor(
    private val apiService: ApiService,
    private val schedulers: BaseSchedulers,
    private val storageUtil: StorageUtil,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {


    lateinit var city: String

    var messages: ObservableList<Message> = ObservableArrayList<Message>()
//    var itemBinding: ItemBinding<Message>? = ItemBinding.of(BR.message, R.layout.view_message_item)
//
//    fun setupMessageClickListener(clickListener: OnMessageClick) {
//        itemBinding?.bindExtra(BR.listener, clickListener)
//    }

    fun setCityAndSaveToPreferences(city: String) {
        this.city = city
        storageUtil.saveCityName(city)
    }

    fun getMessages() {
        inProgress.set(true)
        disposable.add(
            apiService.fetchMessagesForCity(city)
                .subscribeOn(schedulers.io())
                .doAfterTerminate { inProgress.set(false) }
                .observeOn(schedulers.main())
                .subscribe(
                    { replaceMessages(it) },
                    { defaultErrorAction(it) })
        )
    }

    private fun replaceMessages(newMessagesList: List<Message>) {
        messages.clear()
        messages.addAll(newMessagesList)
    }

    interface OnMessageClick {
        fun onClick(message: Message)
    }
}