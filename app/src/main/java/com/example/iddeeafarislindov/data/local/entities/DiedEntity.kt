package com.example.iddeeafarislindov.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "died_records")
data class DiedEntity(
    @PrimaryKey val id: String,

    val entity: String,
    val canton: String?,
    val municipality: String,
    val institution: String,
    val year: Int,
    val month: Int,
    val dateUpdate: String,
    val maleTotal: Int,
    val femaleTotal: Int,
    val total: Int
)