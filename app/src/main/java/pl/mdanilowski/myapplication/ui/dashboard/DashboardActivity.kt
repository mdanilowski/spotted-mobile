package pl.mdanilowski.myapplication.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.databinding.ActivityDashboardBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(), DashboardView {

    companion object {
        const val CITY_NAME = "city_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_dashboard, DashboardViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.city = intent.getStringExtra(CITY_NAME)
        viewModel.getMessages()
    }
}

fun Activity.startDashboardActivity(city: String) =
    Intent(this, DashboardActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .putExtra(DashboardActivity.CITY_NAME, city)
        .let(this::startActivity)