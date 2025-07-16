package com.example.iddeeafarislindov.screens.main

data class MainUiState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val datasetList: List<DatasetItem> = emptyList(),
    val errorMessage: String? = null
)