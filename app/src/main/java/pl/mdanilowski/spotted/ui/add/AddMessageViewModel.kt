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
    private val schedulers: BaseSchedulers,
    private val storageUtil: StorageUtil,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    var message: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)
    var tags: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)

    // TODO: Implement
}