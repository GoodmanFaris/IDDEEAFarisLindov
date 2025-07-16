package com.example.iddeeafarislindov.screens.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DatasetItem(
    val id: String,
    val title: String,
    val description: String
)


class MainViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val SEARCH_QUERY_KEY = "searchQuery"
    }

    val searchQuery: StateFlow<String> = state.getStateFlow(SEARCH_QUERY_KEY, "")



    private val _uiState = MutableStateFlow(MainUiState())

    init {
        loadDatasets()

        viewModelScope.launch {
            searchQuery.collect { query ->
                _uiState.value = _uiState.value.copy(searchQuery = query)
            }
        }
    }


    val uiState: StateFlow<MainUiState> = _uiState

    init {
        loadDatasets()
    }

    private fun loadDatasets() {
        val query = state.get<String>(SEARCH_QUERY_KEY) ?: ""
        _uiState.value = MainUiState(
            searchQuery = query,
            datasetList = listOf(
                DatasetItem("persons", "Broj osoba", "Statistika o ukupnom broju osoba"),
                DatasetItem("idcards", "Izdate lične karte", "Pregled izdatih ID dokumenata"),
                DatasetItem("vehicles", "Registrovana vozila", "Statistika o registrovanim vozilima"),
                DatasetItem("died", "Broj umrlih", "Podaci o prijavljenim smrtnim slučajevima")
            )
        )
    }

    fun onSearchQueryChanged(newQuery: String) {
        state[SEARCH_QUERY_KEY] = newQuery
        _uiState.value = _uiState.value.copy(searchQuery = newQuery)
    }

}


