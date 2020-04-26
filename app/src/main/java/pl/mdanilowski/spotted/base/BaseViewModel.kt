package pl.mdanilowski.spotted.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import pl.mdanilowski.spotted.util.ErrorHandler

open class BaseViewModel(private val errorHandler: ErrorHandler) : ViewModel() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    val inProgress = ObservableBoolean(false)

    protected val defaultErrorConsumer: Consumer<Throwable> = Consumer { defaultErrorAction(it) }

    protected val defaultErrorAction: ((throwable: Throwable) -> Unit) = { throwable: Throwable ->
        inProgress.set(false)
        errorHandler.handleError(throwable) {  }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}