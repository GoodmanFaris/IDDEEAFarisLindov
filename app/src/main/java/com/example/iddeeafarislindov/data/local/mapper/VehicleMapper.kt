package com.example.iddeeafarislindov.data.local.mapper


import com.example.iddeeafarislindov.data.local.entities.VehicleEntity
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle

fun RegisteredVehicle.toEntity(): VehicleEntity {
    return VehicleEntity(
        id = "$registrationPlace-$year-$month",
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

fun VehicleEntity.toRecord(): RegisteredVehicle {
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
