package pl.mdanilowski.spotted.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.databinding.ActivityDashboardBinding

class DashboardActivity :
    BaseActivity<ActivityDashboardBinding, DashboardViewModel>(DashboardViewModel::class) {

    companion object {
        const val CITY_NAME = "city_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_dashboard, DashboardViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
//        vm.setCityAndSaveToPreferences(intent.getStringExtra(CITY_NAME))
//        vm.setupMessageClickListener(object : DashboardViewModel.OnMessageClick {
//            override fun onClick(message: Message) = startMessageDetailsActivity(this@DashboardActivity, message)
//        })
//        vm.getMessages()
//        setupCLickListeners()
//        setupToolbar()
    }

//    fun setupCLickListeners() {
//        binding.fabAddMessage.setOnClickListener { startAddMessageActivity(this) }
//    }
//
//    private fun setupToolbar() {
//        setSupportActionBar(binding.toolbar.findViewById(R.id.toolbar))
//        supportActionBar?.title = "Spotted " + storageUtil.getCityName()
//    }
}

fun Activity.startDashboardActivity(city: String) =
    Intent(this, DashboardActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .putExtra(DashboardActivity.CITY_NAME, city)
        .let(this::startActivity)