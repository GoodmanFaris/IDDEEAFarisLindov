package com.example.iddeeafarislindov.data.local.mapper.favorite

import com.example.iddeeafarislindov.data.local.entities.favorite.FavoritePersonsEntity
import com.example.iddeeafarislindov.data.models.persons.PersonRecord

fun PersonRecord.toFavoriteEntity(): FavoritePersonsEntity {
    return FavoritePersonsEntity(
        id = "$institution-$year-$month",
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        withResidenceTotal = withResidenceTotal,
        total = total
    )
}

fun FavoritePersonsEntity.toRecord(): PersonRecord {
    return PersonRecord(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        withResidenceTotal = withResidenceTotal,
        total = total
    )
}