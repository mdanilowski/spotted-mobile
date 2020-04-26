package pl.mdanilowski.spotted.ui.details

import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.data.model.Message
import pl.mdanilowski.spotted.util.ErrorHandler

class MessageDetailsViewModel constructor(errorHandler: ErrorHandler) :
    BaseViewModel(errorHandler) {

    lateinit var message: Message
}