package com.example.iddeeafarislindov.data.models.persons

data class PersonRecord(
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