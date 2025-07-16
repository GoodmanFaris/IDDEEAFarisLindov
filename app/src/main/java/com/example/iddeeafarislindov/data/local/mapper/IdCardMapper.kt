package com.example.iddeeafarislindov.data.local.mapper


import com.example.iddeeafarislindov.data.local.entities.IdCardEntity
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard

fun IssuedIdCard.toEntity(): IdCardEntity {
    return IdCardEntity(
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

fun IdCardEntity.toRecord(): IssuedIdCard {
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
