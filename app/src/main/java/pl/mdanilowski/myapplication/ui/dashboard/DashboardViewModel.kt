package pl.mdanilowski.myapplication.ui.dashboard

import androidx.lifecycle.AndroidViewModel
import pl.mdanilowski.myapplication.base.WishListApplication
import pl.mdanilowski.myapplication.data.ApiService
import javax.inject.Inject

interface DashboardView {

    fun diplayMessages()
}

class DashboardViewModel @Inject constructor(application: WishListApplication) : AndroidViewModel(application) {

    private lateinit var api: ApiService


}