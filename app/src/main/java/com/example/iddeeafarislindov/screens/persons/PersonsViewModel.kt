package com.example.iddeeafarislindov.screens.persons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.iddeeafarislindov.data.local.AppDatabase
import com.example.iddeeafarislindov.data.models.persons.PersonRecord
import com.example.iddeeafarislindov.data.models.persons.PersonsRequest
import com.example.iddeeafarislindov.data.repository.AppRepository
import com.example.iddeeafarislindov.data.repository.FavoritePersonsRepository
import com.example.iddeeafarislindov.screens.idcards.IdCardsUiState
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonsViewModel(private val db: AppDatabase) : ViewModel() {

    private val repository = AppRepository(db)

    private val _uiState = MutableStateFlow(PersonsUiState())
    val uiState: StateFlow<PersonsUiState> = _uiState

    private val favoriteRepo = FavoritePersonsRepository(db.favoritePersonsDao())
    val favoritePersons: Flow<List<PersonRecord>> = favoriteRepo.favorites


    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }


    fun loadPersons(entityId: Int, year: String, month: String) {
        _uiState.value = PersonsUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val data = repository.getPersonsByRecordDate(
                    PersonsRequest(
                        entityId = entityId,
                        cantonId = 0,
                        municipalityId = 0,
                        year = year,
                        month = month
                    )
                )
                _uiState.value = PersonsUiState(
                    persons = data,
                    entityId = entityId,
                    year = year,
                    month = month
                )
            } catch (e: Exception) {
                _uiState.value = PersonsUiState(errorMessage = e.message)
            }
        }
    }

    fun setSortOption(option: SortOption) {
        _uiState.value = _uiState.value.copy(sortOption = option)
    }


    suspend fun isFavorite(person: PersonRecord): Boolean {
        return favoriteRepo.isFavorite(person)
    }

    fun addToFavorites(person: PersonRecord) {
        viewModelScope.launch {
            favoriteRepo.addToFavorites(person)
        }
    }

    fun removeFromFavorites(person: PersonRecord) {
        viewModelScope.launch {
            favoriteRepo.removeFromFavorites(person)
        }
    }





}

class PersonsViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonsViewModel(db) as T
    }
}
