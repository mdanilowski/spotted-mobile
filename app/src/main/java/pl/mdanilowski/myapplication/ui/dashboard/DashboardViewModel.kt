package pl.mdanilowski.myapplication.ui.dashboard

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import me.tatarka.bindingcollectionadapter2.ItemBinding
import pl.mdanilowski.myapplication.BR
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseView
import pl.mdanilowski.myapplication.base.BaseViewModel
import pl.mdanilowski.myapplication.data.ApiService
import pl.mdanilowski.myapplication.data.model.Message
import pl.mdanilowski.myapplication.util.BaseSchedulers
import javax.inject.Inject

interface DashboardView : BaseView

class DashboardViewModel @Inject constructor(
    private val schedulers: BaseSchedulers
) :
    BaseViewModel<DashboardView>() {

    @Inject
    lateinit var api: ApiService

    lateinit var city: String

    var messages: ObservableList<Message> = ObservableArrayList<Message>()
    var itemBinding: ItemBinding<Message> = ItemBinding.of(BR.message, R.layout.view_message_item)

    fun getMessages() {
        inProgress.set(true)
        disposable.add(
            api.fetchMessagesForCity(city)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .doOnEach { inProgress.set(false) }
                .subscribe(
                    { replaceMessages(it) },
                    { defaultErrorAction(it) })
        )
    }

    private fun replaceMessages(newMessagesList: List<Message>) {
        messages.clear()
        messages.addAll(newMessagesList)
    }
}