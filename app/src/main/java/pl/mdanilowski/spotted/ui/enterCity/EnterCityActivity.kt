package pl.mdanilowski.spotted.ui.enterCity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.databinding.ActivityEnterCityBinding
import timber.log.Timber

class EnterCityActivity :
    BaseActivity<ActivityEnterCityBinding, EnterCityViewModel>(EnterCityViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_enter_city, EnterCityViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.getAvailableCities()

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        Timber.i("")
    }
}

fun Activity.startEnterCityActivity(context: Context) {
    startActivity(
        Intent(context, EnterCityActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}