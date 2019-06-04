package pl.mdanilowski.myapplication.util

import pl.mdanilowski.myapplication.R
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

interface ErrorHandler {
    fun handleError(throwable: Throwable?, execute: (errorMessage: String) -> Unit = {})
}

class ErrorHandlerImpl @Inject constructor(private val stringProvider: StringProvider) : ErrorHandler {
    override fun handleError(throwable: Throwable?, execute: (errorMessage: String) -> Unit) {
        // TODO handle errors
        Timber.e(throwable)
        execute(getErrorMessage(throwable))
    }

    private fun getErrorMessage(throwable: Throwable?): String {
        return when (throwable) {
            is UnknownHostException -> stringProvider.getString(R.string.error_offline)
            is SocketTimeoutException -> stringProvider.getString(R.string.error_timeout)
            else -> throwable?.localizedMessage
                ?: stringProvider.getString(R.string.error_unknown)
        }
    }
}
