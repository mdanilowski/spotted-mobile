package pl.mdanilowski.myapplication.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.databinding.ActivityDashboardBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(), DashboardView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_enter_city, DashboardViewModel::class.java)
        binding.lifecycleOwner = this
    }

    override fun diplayMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun Activity.startDashboardActivity() =
    Intent(this, DashboardActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .let(this::startActivity)