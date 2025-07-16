package com.example.iddeeafarislindov.screens.idcards



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.iddeeafarislindov.data.local.AppDatabase
import com.example.iddeeafarislindov.data.models.idcard.IdCardsRequest
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.data.repository.AppRepository
import com.example.iddeeafarislindov.data.repository.FavoriteIdCardRepository
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IdCardsViewModel(private val db: AppDatabase) : ViewModel() {
    private val repository = AppRepository(db)

    private val _uiState = MutableStateFlow(IdCardsUiState())
    val uiState: StateFlow<IdCardsUiState> = _uiState

    private val favoriteRepo = FavoriteIdCardRepository(db.favoriteIdCardDao())
    val favoriteIdCards: Flow<List<IssuedIdCard>> = favoriteRepo.favorites


    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }



    fun loadIdCards(entityId: Int, year: String, month: String) {
        _uiState.value = IdCardsUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val data = repository.getIssuedIdCards(
                    IdCardsRequest(
                        entityId = entityId,
                        year = year,
                        month = month
                    )
                )
                _uiState.value = IdCardsUiState(
                    idCards = data,
                    entityId = entityId,
                    year = year,
                    month = month
                )

            } catch (e: Exception) {
                _uiState.value = IdCardsUiState(errorMessage = e.message)
            }
        }
    }
    fun setSortOption(option: SortOption) {
        _uiState.value = _uiState.value.copy(sortOption = option)
    }



    suspend fun isFavorite(card: IssuedIdCard): Boolean {
        return favoriteRepo.isFavorite(card)
    }

    fun addToFavorites(card: IssuedIdCard) {
        viewModelScope.launch {
            favoriteRepo.addToFavorites(card)
        }
    }

    fun removeFromFavorites(card: IssuedIdCard) {
        viewModelScope.launch {
            favoriteRepo.removeFromFavorites(card)
        }
    }

}



class IdCardsViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IdCardsViewModel::class.java)) {
            return IdCardsViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


