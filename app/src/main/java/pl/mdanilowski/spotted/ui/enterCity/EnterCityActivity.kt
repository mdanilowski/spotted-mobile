package pl.mdanilowski.spotted.ui.enterCity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.toolbar_default.view.*
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.data.model.City
import pl.mdanilowski.spotted.databinding.ActivityEnterCityBinding
import pl.mdanilowski.spotted.extensions.setupToolbarWithoutBackNavigation
import timber.log.Timber

class EnterCityActivity :
    BaseActivity<ActivityEnterCityBinding, EnterCityViewModel>(EnterCityViewModel::class) {

    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_enter_city, EnterCityViewModel::class.java)
        setupToolbar()
        binding.lifecycleOwner = this

        setupRecyclerView()
        observeCities()
        viewModel.getAvailableCities()
    }

    private fun setupToolbar() {
        setupToolbarWithoutBackNavigation(binding.toolbar.customToolbar)
        binding.toolbar.toolbarText.text = resources.getString(R.string.select_city)
    }

    private fun setupRecyclerView() {
        citiesAdapter = CitiesAdapter(object : CitiesAdapter.OnCityClicked {
            override fun onClick(city: City) {
                Timber.d(city.name)
            }
        })
        binding.citySelectRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.citySelectRv.adapter = citiesAdapter
    }

    private fun observeCities() {
        viewModel.cities.observe(this, Observer { cities -> citiesAdapter.setCities(cities) })
    }
}

fun Activity.startEnterCityActivity(context: Context) {
    startActivity(
        Intent(context, EnterCityActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}