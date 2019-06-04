package pl.mdanilowski.myapplication.ui.dashboard

import androidx.lifecycle.AndroidViewModel
import pl.mdanilowski.myapplication.base.WishListApplication
import javax.inject.Inject

class DashboardViewModel @Inject constructor(application: WishListApplication) : AndroidViewModel(application) {

}

data class DashboardButton(val text: String, val color: Int)