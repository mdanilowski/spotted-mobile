package pl.mdanilowski.spotted.ui.dashboard

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.data.ApiService
import pl.mdanilowski.spotted.data.model.Message
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler
import pl.mdanilowski.spotted.util.StorageUtil

// TODO : CONVERT TO INTERACTOR USAGE
class DashboardViewModel constructor(
    private val apiService: ApiService,
    private val schedulers: BaseSchedulers,
    private val storageUtil: StorageUtil,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    lateinit var city: String

    private var messages: ObservableList<Message> = ObservableArrayList<Message>()

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
                    { errorHandler.handleError(it) })
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