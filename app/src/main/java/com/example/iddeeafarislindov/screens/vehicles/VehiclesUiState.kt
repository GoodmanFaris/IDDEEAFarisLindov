package com.example.iddeeafarislindov.screens.vehicles


import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption


data class VehiclesUiState(
    val isLoading: Boolean = false,
    val vehicles: List<RegisteredVehicle> = emptyList(),
    val errorMessage: String? = null,
    val sortOption: SortOption = SortOption.BY_TOTAL_DESC,
    val searchQuery: String = "",
    val entityId: Int = 0,
    val year: String = "",
    val month: String = ""
) {
    val sortedList: List<RegisteredVehicle>
        get() {
            val entityName = when (entityId) {
                1 -> "FEDERACIJA BOSNE I HERCEGOVINE"
                2 -> "REPUBLIKA SRPSKA"
                3 -> "BRČKO DISTRIKT BOSNE I HERCEGOVINE"
                else -> ""
            }

            return vehicles
                .filter {
                    (entityName.isBlank() || it.entity.equals(entityName, ignoreCase = true)) &&
                            (year.isBlank() || it.year.toString() == year) &&
                            (month.isBlank() || it.month.toString() == month) &&
                            (
                                    it.municipality.contains(searchQuery.trim(), ignoreCase = true) ||
                                            it.registrationPlace.contains(searchQuery.trim(), ignoreCase = true)
                                    )
                }
                .let { list ->
                    when (sortOption) {
                        SortOption.BY_TOTAL_DESC -> list.sortedByDescending { it.total }
                        SortOption.BY_TOTAL_ASC -> list.sortedBy { it.total }
                        SortOption.BY_MUNICIPALITY -> list.sortedBy { it.municipality }
                    }
                }
        }
}
