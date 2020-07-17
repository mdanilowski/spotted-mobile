package pl.mdanilowski.spotted.application.cities.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import pl.mdanilowski.spotted.application.cities.data.dao.CityDao
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.cities.domain.model.City
import pl.mdanilowski.spotted.extensions.models.toEntity
import pl.mdanilowski.spotted.network.ApiService
import pl.mdanilowski.spotted.util.StorageUtil
import timber.log.Timber

interface CitiesRepository {

    fun saveSelectedCityId(cityId: Long): Completable

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

    override fun saveSelectedCityId(cityId: Long): Completable =
        Completable.create { storageUtil.saveCityId(cityId) }

    override fun getSelectedCityId(): Long? = storageUtil.getCityId()

    override fun getSelectedCityName(cityId: Long): LiveData<String> =
        cityDao.observeCityName(cityId)

    /* Get cities use case */
    override fun getAllCities(): LiveData<List<CityEntity>> = cityDao.getAllCities()

    /* Get cities use case */
    override fun fetchAndPersistCities(): Completable = apiService.getAllAvailableCities()
        .flatMapCompletable { cities ->
            return@flatMapCompletable cityDao.deleteAllCities()
                .doOnComplete { Timber.i("DELETE COMPLETED") }
                .andThen(
                    cityDao.insertCities(cities.map { it.toEntity() })
                )
        }
}
