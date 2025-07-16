package com.example.iddeeafarislindov.screens.died

import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption

data class DiedUiState(
    val isLoading: Boolean = false,
    val diedList: List<DiedRecord> = emptyList(),
    val errorMessage: String? = null,
    val sortOption: SortOption = SortOption.BY_TOTAL_DESC,
    val searchQuery: String = "",
    val entityId: Int? = null,
    val year: String = "",
    val month: String = ""
) {
    val sortedList: List<DiedRecord>
        get() {
            val entityName = when (entityId) {
                1 -> "FEDERACIJA BOSNE I HERCEGOVINE"
                2 -> "REPUBLIKA SRPSKA"
                3 -> "BRČKO DISTRIKT BOSNE I HERCEGOVINE"
                else -> ""
            }

            return diedList
                .filter {
                    (entityName.isBlank() || it.entity.equals(entityName, ignoreCase = true)) &&
                            (year.isBlank() || it.year.toString() == year) &&
                            (month.isBlank() || it.month.toString() == month) &&
                            (it.municipality.contains(searchQuery.trim(), ignoreCase = true) ||
                                    it.institution.contains(searchQuery.trim(), ignoreCase = true))
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

