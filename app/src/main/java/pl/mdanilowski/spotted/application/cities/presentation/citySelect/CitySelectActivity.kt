package pl.mdanilowski.spotted.application.cities.presentation.citySelect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.toolbar_default.view.*
import pl.mdanilowski.spotted.R
import pl.mdanilowski.spotted.base.BaseActivity
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.posts.presentation.postsDashboard.startPostsDashboardActivity
import pl.mdanilowski.spotted.databinding.ActivityCitySelectBinding
import pl.mdanilowski.spotted.extensions.setupToolbarWithoutBackNavigation
import timber.log.Timber

class CitySelectActivity :
    BaseActivity<ActivityCitySelectBinding, CitySelectViewModel>(CitySelectViewModel::class) {

    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup(R.layout.activity_city_select, CitySelectViewModel::class.java)
        setupToolbar()
        binding.lifecycleOwner = this

        setupRecyclerView()
        observeCities()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchAllCities()
    }

    private fun setupToolbar() {
        setupToolbarWithoutBackNavigation(binding.toolbar.customToolbar)
        binding.toolbar.toolbarText.text = resources.getString(R.string.select_city)
    }

    private fun setupRecyclerView() {
        citiesAdapter = CitiesAdapter(object : CitiesAdapter.OnCityClicked {
            override fun onClick(city: CityEntity) {
                startPostsDashboardActivity(city.id)
            }
        })
        binding.citySelectRv.layoutManager = LinearLayoutManager(this)
        binding.citySelectRv.adapter = citiesAdapter
    }

    private fun observeCities() {
        viewModel.getCities().observe(this, Observer { cities -> citiesAdapter.setCities(cities) })
    }
}

fun Activity.startEnterCityActivity(context: Context) {
    startActivity(
        Intent(context, CitySelectActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}