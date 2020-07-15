package pl.mdanilowski.spotted.application.cities.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import pl.mdanilowski.spotted.application.cities.data.dao.CityDao
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.cities.domain.model.City
import pl.mdanilowski.spotted.network.ApiService
import pl.mdanilowski.spotted.util.BaseSchedulers
import pl.mdanilowski.spotted.util.StorageUtil

interface CitiesRepository {

    fun saveSelectedCityId(cityId: Long)

    fun getSelectedCityId(): Long?

    fun getSelectedCityName(cityId: Long): LiveData<String>

    fun getAllCities(): LiveData<List<CityEntity>>

    fun fetchAndPersistCities(): Completable
}

class CitiesRepositoryImpl(
    private val storageUtil: StorageUtil,
    private val cityDao: CityDao,
    private val apiService: ApiService
) :
    CitiesRepository {

    override fun saveSelectedCityId(cityId: Long) = storageUtil.saveCityId(cityId)

    override fun getSelectedCityId(): Long? = storageUtil.getCityId()

    override fun getSelectedCityName(cityId: Long): LiveData<String> =
        cityDao.getCityNameLiveData(cityId)

    /* Get cities use case */
    override fun getAllCities(): LiveData<List<CityEntity>> = cityDao.getAllCities()

    /* Get cities use case */
    override fun fetchAndPersistCities(): Completable = apiService.getAllAvailableCities()
        .doOnSuccess { cityDao.deleteAllCities() }
        .flatMapCompletable { cities -> cityDao.insertCities(mapToCityEntities(cities)) }

    private fun mapToCityEntities(cities: List<City>): List<CityEntity> = cities.map { city ->
        CityEntity(
            city.name,
            city.image
        )
    }.toList()
}
