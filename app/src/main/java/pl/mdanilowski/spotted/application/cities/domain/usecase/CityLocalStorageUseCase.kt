package pl.mdanilowski.spotted.application.cities.domain.usecase

import pl.mdanilowski.spotted.application.cities.domain.repository.CitiesRepository

interface CityLocalStorageUseCase {
    fun getSelectedCity() : Long?

    fun updateSelectedCity(cityId: Long)
}

class CityLocalStorageUseCaseImpl(private val citiesRepository: CitiesRepository) : CityLocalStorageUseCase {
    override fun getSelectedCity() = citiesRepository.getSelectedCityId()

    override fun updateSelectedCity(cityId: Long) {
        citiesRepository.saveSelectedCityId(cityId)
    }
}