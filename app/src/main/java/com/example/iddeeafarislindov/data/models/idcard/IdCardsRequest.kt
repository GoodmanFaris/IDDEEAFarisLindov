package com.example.iddeeafarislindov.data.models.idcard


data class IdCardsRequest(
    val entityId: Int = 0,
    val cantonId: Int = 0,
    val municipalityId: Int = 0,
    val year: String = "",
    val month: String = ""
)

