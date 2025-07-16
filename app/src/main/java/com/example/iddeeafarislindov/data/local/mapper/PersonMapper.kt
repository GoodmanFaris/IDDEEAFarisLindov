package com.example.iddeeafarislindov.data.local.mapper


import com.example.iddeeafarislindov.data.local.entities.PersonEntity
import com.example.iddeeafarislindov.data.models.persons.PersonRecord

fun PersonRecord.toEntity(): PersonEntity {
    return PersonEntity(
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

fun PersonEntity.toRecord(): PersonRecord {
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