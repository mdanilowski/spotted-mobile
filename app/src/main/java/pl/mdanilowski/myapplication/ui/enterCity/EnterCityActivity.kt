package pl.mdanilowski.myapplication.ui.enterCity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.databinding.ActivityEnterCityBinding

class EnterCityActivity : BaseActivity<ActivityEnterCityBinding, EnterCityViewModel>(), EnterCityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_enter_city, EnterCityViewModel::class.java)
        binding.lifecycleOwner = this
    }
}

fun Activity.startEnterCityActivity(context: Context) {
    startActivity(Intent(context, EnterCityActivity::class.java))
}