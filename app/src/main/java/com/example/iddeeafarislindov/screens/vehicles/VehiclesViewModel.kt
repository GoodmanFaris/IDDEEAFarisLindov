package com.example.iddeeafarislindov.screens.vehicles


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.iddeeafarislindov.data.local.AppDatabase
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.data.models.vehicles.VehiclesRequest
import com.example.iddeeafarislindov.data.repository.AppRepository
import com.example.iddeeafarislindov.data.repository.FavoriteVehicleRepository
import com.example.iddeeafarislindov.screens.persons.PersonsUiState
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.jvm.java

class VehiclesViewModel(private val db: AppDatabase): ViewModel() {
    private val repository = AppRepository(db)

    private val _uiState = MutableStateFlow(VehiclesUiState())
    val uiState: StateFlow<VehiclesUiState> = _uiState

    private val favoriteRepo = FavoriteVehicleRepository(db.favoriteVehicleDao())
    val favoriteVehicles: Flow<List<RegisteredVehicle>> = favoriteRepo.favorites


    fun setSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }


    fun loadVehicles(entityId: Int, year: String, month: String) {
        _uiState.value = VehiclesUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val data = repository.getRegisteredVehicles(
                    VehiclesRequest(
                        entityId = entityId,
                        year = year,
                        month = month
                    )
                )
                _uiState.value = VehiclesUiState(
                    vehicles = data,
                    entityId = entityId,
                    year = year,
                    month = month
                )
            } catch (e: Exception) {
                _uiState.value = VehiclesUiState(errorMessage = e.message)
            }
        }
    }

    fun setSortOption(option: SortOption) {
        _uiState.value = _uiState.value.copy(sortOption = option)
    }


    suspend fun isFavorite(vehicle: RegisteredVehicle): Boolean {
        return favoriteRepo.isFavorite(vehicle)
    }

    fun addToFavorites(vehicle: RegisteredVehicle) {
        viewModelScope.launch {
            favoriteRepo.addToFavorites(vehicle)
        }
    }

    fun removeFromFavorites(vehicle: RegisteredVehicle) {
        viewModelScope.launch {
            favoriteRepo.removeFromFavorites(vehicle)
        }
    }


}

class VehicleViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehiclesViewModel::class.java)) {
            return VehiclesViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}