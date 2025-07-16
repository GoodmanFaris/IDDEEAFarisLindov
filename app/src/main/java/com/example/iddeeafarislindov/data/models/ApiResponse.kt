package com.example.iddeeafarislindov.data.models

data class ApiResponse<T>(
    val success: Boolean,
    val result: List<T>,
    val errors: List<String>?
)

