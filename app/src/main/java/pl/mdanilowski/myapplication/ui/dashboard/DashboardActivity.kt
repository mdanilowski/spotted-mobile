package pl.mdanilowski.myapplication.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_dashboard.*
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.databinding.ActivityDashboardBinding
import pl.mdanilowski.myapplication.dagger.ViewModelFactory
import javax.inject.Inject

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_dashboard, DashboardViewModel::class.java)
        binding.lifecycleOwner = this
    }

}

fun Activity.startDashboardActivity() =
    Intent(this, DashboardActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .let(this::startActivity)