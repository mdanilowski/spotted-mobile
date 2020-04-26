package pl.mdanilowski.spotted.data.interactors

import io.reactivex.Completable
import io.reactivex.Single
import pl.mdanilowski.spotted.data.db.dao.CityDao
import pl.mdanilowski.spotted.data.db.entity.CityEntity
import pl.mdanilowski.spotted.data.model.City

interface DatabaseInteractor {
    fun getAllCities(): Single<List<City>>

    fun saveAllCities(cities: List<City>): Completable
}

class DatabaseInteractorImpl(private val cityDao: CityDao) : DatabaseInteractor {

    override fun getAllCities(): Single<List<City>> {
        return cityDao.getAllCities().map { convertToModel(it) }
    }

    private fun convertToModel(cities: List<CityEntity>?): List<City>? =
        cities?.map { City(it.name, null) }?.toList()

    override fun saveAllCities(cities: List<City>): Completable {
        return cityDao.insertCities(convertToEntities(cities))
    }

    private fun convertToEntities(cities: List<City>): List<CityEntity> =
        cities.map { CityEntity(id = null, name = it.name) }
}