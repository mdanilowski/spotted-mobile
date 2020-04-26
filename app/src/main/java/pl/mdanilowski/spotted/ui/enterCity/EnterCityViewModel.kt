package pl.mdanilowski.spotted.ui.enterCity

import androidx.lifecycle.MutableLiveData
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.data.model.City
import pl.mdanilowski.spotted.repository.CitiesRepository
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler

class EnterCityViewModel constructor(
    private val citiesRepository: CitiesRepository,
    private val baseSchedulers: BaseSchedulers,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val cities: MutableLiveData<List<City>> by lazy { MutableLiveData<List<City>>() }

    fun getAvailableCities() {
        disposable.add(
            citiesRepository.getAllCities()
                .subscribeOn(baseSchedulers.io())
                .observeOn(baseSchedulers.main())
                .subscribe({
                    cities.value = it
                }, {
                    defaultErrorConsumer.accept(it)
                })
        )
    }
}