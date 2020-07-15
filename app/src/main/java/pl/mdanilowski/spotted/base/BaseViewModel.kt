package pl.mdanilowski.spotted.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler

open class BaseViewModel(val baseSchedulers: BaseSchedulers, val errorHandler: ErrorHandler) :
    ViewModel() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    val inProgress = ObservableBoolean(false)

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}