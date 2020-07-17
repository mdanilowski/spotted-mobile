package pl.mdanilowski.spotted.application.cities.domain.usecase

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.cities.domain.repository.CitiesRepository

interface GetCitiesUseCase {

    fun getPersistedCities() : LiveData<List<CityEntity>>

    fun getSelectedCityName(cityId: Long) : LiveData<String>

    fun fetchAllCitiesAndPersist() : Completable
}

class GetCitiesUseCaseImpl(private val citiesRepository: CitiesRepository) : GetCitiesUseCase {
    override fun getPersistedCities(): LiveData<List<CityEntity>> {
        return citiesRepository.getAllCities()
    }

    override fun getSelectedCityName(cityId: Long): LiveData<String> {
        return citiesRepository.getSelectedCityName(cityId)
    }

    override fun fetchAllCitiesAndPersist(): Completable {
        return citiesRepository.fetchAndPersistCities()
    }
}