package com.example.iddeeafarislindov.screens.died

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.iddeeafarislindov.data.local.AppDatabase
import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.data.models.died.DiedRequest
import com.example.iddeeafarislindov.data.repository.AppRepository
import com.example.iddeeafarislindov.data.repository.FavoriteDiedRepository
import com.example.iddeeafarislindov.screens.idcards.IdCardsUiState
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiedViewModel(private val db: AppDatabase) : ViewModel() {
    private val repository = AppRepository(db)

    private val _uiState = MutableStateFlow(DiedUiState())
    val uiState: StateFlow<DiedUiState> = _uiState

    private val favoriteRepo = FavoriteDiedRepository(db.favoriteDiedDao())
    val favoriteDied: Flow<List<DiedRecord>> = favoriteRepo.favorites


    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }


    fun loadDied(entityId: Int, year: String, month: String) {
        _uiState.value = DiedUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val data = repository.getDied(
                    DiedRequest(
                        entityId = entityId,
                        year = year,
                        month = month
                    )
                )
                _uiState.value = DiedUiState(
                    diedList = data,
                    entityId = entityId,
                    year = year,
                    month = month
                )
            } catch (e: Exception) {
                _uiState.value = DiedUiState(errorMessage = e.message)
            }
        }
    }



    private val _sortOption = MutableStateFlow(DiedSortOption.BY_TOTAL_DESC)
    val sortOption: StateFlow<DiedSortOption> = _sortOption

    fun setSortOption(option: SortOption) {
        _uiState.value = _uiState.value.copy(sortOption = option)
    }


    suspend fun isFavorite(record: DiedRecord): Boolean {
        return favoriteRepo.isFavorite(record)
    }

    fun addToFavorites(record: DiedRecord) {
        viewModelScope.launch {
            favoriteRepo.addToFavorites(record)
        }
    }

    fun removeFromFavorites(record: DiedRecord) {
        viewModelScope.launch {
            favoriteRepo.removeFromFavorites(record)
        }
    }

}

class DiedViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiedViewModel::class.java)) {
            return DiedViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

enum class DiedSortOption {
    BY_TOTAL_DESC, BY_TOTAL_ASC, BY_MUNICIPALITY
}

