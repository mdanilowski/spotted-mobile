package pl.mdanilowski.spotted.repository

import io.reactivex.Observable
import io.reactivex.Single
import pl.mdanilowski.spotted.data.interactors.DatabaseInteractor
import pl.mdanilowski.spotted.data.interactors.NetworkInteractor
import pl.mdanilowski.spotted.data.model.City

interface CitiesRepository {
    fun getAllCities(): Observable<List<City>>
}

class CitiesRepositoryImpl(
    private val databaseInteractor: DatabaseInteractor,
    private val networkInteractor: NetworkInteractor
) : CitiesRepository {

    override fun getAllCities(): Observable<List<City>> {
        val databaseSingle : Observable<List<City>>? = databaseInteractor.getAllCities().toObservable()
        val networkSingle : Observable<List<City>>? = networkInteractor.getCities().toObservable()

        return Observable.merge(databaseSingle, networkSingle)
    }
}