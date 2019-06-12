package pl.mdanilowski.myapplication.ui.enterCity

import androidx.databinding.ObservableField
import pl.mdanilowski.myapplication.base.BaseView
import pl.mdanilowski.myapplication.base.BaseViewModel
import pl.mdanilowski.myapplication.util.Constants
import javax.inject.Inject

interface EnterCityView : BaseView {

    fun setupClickListeners()
}

class EnterCityViewModel @Inject constructor() : BaseViewModel<EnterCityView>() {

    var enteredCity: ObservableField<String> = ObservableField(Constants.EMPTY_STRING)

    fun getEnteredCity(): String? {
        val enteredCity = enteredCity.get()
        return when {
            enteredCity.isNullOrBlank().not() -> enteredCity?.trim()
            else -> null
        }
    }
}