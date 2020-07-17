package pl.mdanilowski.spotted.extensions.daoModels

import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.cities.domain.model.City

fun CityEntity.toDomain() : City = City(name, image)
