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
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    val cities: MutableLiveData<List<City>> by lazy { MutableLiveData<List<City>>() }
    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getAvailableCities() {
        inProgress.set(true)
        disposable.add(
            citiesRepository.getAllCities()
                .subscribeOn(baseSchedulers.io())
                .doOnComplete { inProgress.set(false) }
                .observeOn(baseSchedulers.main())
                .subscribe({
                    cities.value = it
                }, {
                    errorHandler.handleError(it) { e -> errorMessage.value = e}
                })
        )
    }
}