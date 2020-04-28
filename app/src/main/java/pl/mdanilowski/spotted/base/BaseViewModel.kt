package pl.mdanilowski.spotted.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    val inProgress = ObservableBoolean(false)

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}