package pl.mdanilowski.spotted.data.interactors

import io.reactivex.Single
import pl.mdanilowski.spotted.data.ApiService
import pl.mdanilowski.spotted.data.model.City

interface NetworkInteractor {
    fun getCities(): Single<List<City>>
}

class NetworkInteractorImpl(
    private val apiService: ApiService,
    private val databaseInteractor: DatabaseInteractor
) : NetworkInteractor {

    override fun getCities(): Single<List<City>> {
        return apiService.getAllAvailableCities()
            .doOnSuccess { databaseInteractor.saveAllCities(it) }
    }
}