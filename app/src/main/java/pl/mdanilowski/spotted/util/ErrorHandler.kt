package pl.mdanilowski.spotted.util

import pl.mdanilowski.spotted.R
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ErrorHandler {
    fun handleError(throwable: Throwable?, execute: (errorMessage: String) -> Unit = {})
}

class ErrorHandlerImpl constructor(private val stringProvider: StringProvider) : ErrorHandler {
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
