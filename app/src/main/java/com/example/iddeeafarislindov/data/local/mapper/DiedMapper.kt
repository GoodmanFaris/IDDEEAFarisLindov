package com.example.iddeeafarislindov.data.local.mapper

import com.example.iddeeafarislindov.data.local.entities.DiedEntity
import com.example.iddeeafarislindov.data.models.died.DiedRecord

fun DiedRecord.toEntity(): DiedEntity {
    return DiedEntity(
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

fun DiedEntity.toRecord(): DiedRecord {
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