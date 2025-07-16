package com.example.iddeeafarislindov.data.models.vehicles



data class RegisteredVehicle(
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
