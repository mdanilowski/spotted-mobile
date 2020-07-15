package pl.mdanilowski.spotted.application.cities.presentation.citySelect

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.mdanilowski.spotted.base.BaseViewModel
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.cities.domain.usecase.GetCitiesUseCase
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.ErrorHandler
import timber.log.Timber

class CitySelectViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    baseSchedulers: BaseSchedulers,
    errorHandler: ErrorHandler
) : BaseViewModel(baseSchedulers, errorHandler) {

    private val cities: LiveData<List<CityEntity>> = getCitiesUseCase.getPersistedCities()
    private val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun getCities() = cities

    fun fetchAllCities() {
        disposable.add(
            getCitiesUseCase.fetchAllCitiesAndPersist()
                .subscribeOn(baseSchedulers.io())
                .doOnError { Timber.e(it) }
                .doOnComplete { Timber.i("Database has been updated with new cities") }
                .subscribe()
        )
    }
}