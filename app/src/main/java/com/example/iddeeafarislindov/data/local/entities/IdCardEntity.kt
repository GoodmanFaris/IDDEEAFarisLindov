package com.example.iddeeafarislindov.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "id_cards")
data class IdCardEntity(
    @PrimaryKey val id: String,

    val entity: String,
    val canton: String?,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val issuedFirstTimeMaleTotal: Int,
    val replacedMaleTotal: Int,
    val issuedFirstTimeFemaleTotal: Int,
    val replacedFemaleTotal: Int,
    val total: Int
)