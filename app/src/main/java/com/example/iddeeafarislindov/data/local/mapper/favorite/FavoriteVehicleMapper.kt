package com.example.iddeeafarislindov.data.local.mapper.favorite

import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteVehicleEntity
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle

fun FavoriteVehicleEntity.toRecord(): RegisteredVehicle {
    return RegisteredVehicle(
        entity = entity,
        canton = canton,
        municipality = municipality,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        registrationPlace = registrationPlace,
        totalDomestic = totalDomestic,
        totalForeign = totalForeign,
        total = total
    )
}

fun RegisteredVehicle.toFavoriteEntity(): FavoriteVehicleEntity {
    return FavoriteVehicleEntity(
        id = "$registrationPlace-$year-$month", // unique ključ
        entity = entity,
        canton = canton,
        municipality = municipality,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        registrationPlace = registrationPlace,
        totalDomestic = totalDomestic,
        totalForeign = totalForeign,
        total = total
    )
}

