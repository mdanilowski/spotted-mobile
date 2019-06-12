package pl.mdanilowski.myapplication.ui.enterCity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pl.mdanilowski.myapplication.R
import pl.mdanilowski.myapplication.base.BaseActivity
import pl.mdanilowski.myapplication.databinding.ActivityEnterCityBinding
import pl.mdanilowski.myapplication.ui.dashboard.startDashboardActivity
import pl.mdanilowski.myapplication.util.StringProvider
import javax.inject.Inject

class EnterCityActivity :
    BaseActivity<ActivityEnterCityBinding, EnterCityViewModel>(), EnterCityView {

    @Inject
    lateinit var stringProvider: StringProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_enter_city, EnterCityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.enterCityViewModel = viewModel
        setupClickListeners()
    }

    override fun setupClickListeners() {
        binding.btnEnter.setOnClickListener {
            viewModel.getEnteredCity()
                ?.also { startDashboardActivity(it) }
                ?: showToastMessage(stringProvider.getString(R.string.no_city_name_error_text))
        }
    }
}

fun Activity.startEnterCityActivity(context: Context) {
    startActivity(
        Intent(context, EnterCityActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}