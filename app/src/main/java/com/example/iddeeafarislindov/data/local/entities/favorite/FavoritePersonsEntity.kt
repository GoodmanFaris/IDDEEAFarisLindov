package com.example.iddeeafarislindov.data.local.entities.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_persons")
data class FavoritePersonsEntity(
    @PrimaryKey val id: String,
    val entity: String,
    val canton: String?,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val withResidenceTotal: Int,
    val total: Int
)