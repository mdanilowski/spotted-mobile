package pl.mdanilowski.myapplication.ui.details

import pl.mdanilowski.myapplication.base.BaseView
import pl.mdanilowski.myapplication.base.BaseViewModel
import pl.mdanilowski.myapplication.data.model.Message
import javax.inject.Inject

interface MessageDetailsView : BaseView

class MessageDetailsViewModel @Inject constructor() : BaseViewModel<MessageDetailsView>() {

    lateinit var message: Message
}