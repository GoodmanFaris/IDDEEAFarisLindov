package com.example.iddeeafarislindov.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class VehicleEntity(
    @PrimaryKey val id: String,

    val entity: String,
    val canton: String,
    val municipality: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val registrationPlace: String,
    val totalDomestic: Int,
    val totalForeign: Int,
    val total: Int
)