package pl.mdanilowski.myapplication.ui.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.data.model.Message
import pl.mdanilowski.myapplication.databinding.ActivityDashboardBinding
import pl.mdanilowski.myapplication.ui.add.startAddMessageActivity
import pl.mdanilowski.myapplication.ui.details.startMessageDetailsActivity
import pl.mdanilowski.myapplication.util.StorageUtil
import javax.inject.Inject

class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(), DashboardView {

    companion object {
        const val CITY_NAME = "city_name"
    }

    @Inject
    lateinit var storageUtil: StorageUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_dashboard, DashboardViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.setCityAndSaveToPreferences(intent.getStringExtra(CITY_NAME))
        viewModel.setupMessageClickListener(object : DashboardViewModel.OnMessageClick {
            override fun onClick(message: Message) = startMessageDetailsActivity(this@DashboardActivity, message)
        })
        viewModel.getMessages()
        setupCLickListeners()
        setupToolbar()
    }

    override fun setupCLickListeners() {
        binding.fabAddMessage.setOnClickListener { startAddMessageActivity(this) }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.findViewById(R.id.toolbar))
        supportActionBar?.title = "Spotted " + storageUtil.getCityName()
    }
}

fun Activity.startDashboardActivity(city: String) =
    Intent(this, DashboardActivity::class.java)
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .putExtra(DashboardActivity.CITY_NAME, city)
        .let(this::startActivity)