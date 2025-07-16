package com.example.iddeeafarislindov.data.local.mapper.favorite

import com.example.iddeeafarislindov.data.local.entities.favorite.FavoriteIdCardEntity
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard


fun IssuedIdCard.toFavoriteEntity(): FavoriteIdCardEntity {
    return FavoriteIdCardEntity(
        id = "$institution-$year-$month",
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        issuedFirstTimeMaleTotal = issuedFirstTimeMaleTotal,
        replacedMaleTotal = replacedMaleTotal,
        issuedFirstTimeFemaleTotal = issuedFirstTimeFemaleTotal,
        replacedFemaleTotal = replacedFemaleTotal,
        total = total
    )
}

fun FavoriteIdCardEntity.toRecord(): IssuedIdCard {
    return IssuedIdCard(
        entity = entity,
        canton = canton,
        municipality = municipality,
        institution = institution,
        year = year,
        month = month,
        dateUpdate = dateUpdate,
        issuedFirstTimeMaleTotal = issuedFirstTimeMaleTotal,
        replacedMaleTotal = replacedMaleTotal,
        issuedFirstTimeFemaleTotal = issuedFirstTimeFemaleTotal,
        replacedFemaleTotal = replacedFemaleTotal,
        total = total
    )
}
