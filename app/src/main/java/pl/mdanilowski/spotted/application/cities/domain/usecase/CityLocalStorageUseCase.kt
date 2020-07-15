package pl.mdanilowski.spotted.application.cities.domain.usecase

import io.reactivex.Completable
import pl.mdanilowski.spotted.application.cities.domain.repository.CitiesRepository

interface CityLocalStorageUseCase {
    fun getSelectedCityId() : Long?

    fun updateSelectedCityId(cityId: Long) : Completable
}

class CityLocalStorageUseCaseImpl(private val citiesRepository: CitiesRepository) : CityLocalStorageUseCase {
    override fun getSelectedCityId() = citiesRepository.getSelectedCityId()

    override fun updateSelectedCityId(cityId: Long) : Completable {
        return citiesRepository.saveSelectedCityId(cityId)
    }
}