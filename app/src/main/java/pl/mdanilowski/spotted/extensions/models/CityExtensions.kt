package pl.mdanilowski.spotted.extensions.models

import pl.mdanilowski.spotted.application.cities.data.entity.CityEntity
import pl.mdanilowski.spotted.application.cities.domain.model.City

fun City.toEntity(): CityEntity = CityEntity(
    this.name,
    this.image
)
