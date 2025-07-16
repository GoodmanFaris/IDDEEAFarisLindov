package com.example.iddeeafarislindov.data.local.mapper.favorite

import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteDiedEntity
import com.example.iddeeafarislindov.data.models.died.DiedRecord



fun DiedRecord.toFavoriteEntity(): FavoriteDiedEntity {
    return FavoriteDiedEntity(
        id = "$institution-$year-$month",
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        maleTotal = maleTotal,
        femaleTotal = femaleTotal,
        total = total
    )
}

fun FavoriteDiedEntity.toRecord(): DiedRecord {
    return DiedRecord(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        maleTotal = maleTotal,
        femaleTotal = femaleTotal,
        total = total
    )
}
