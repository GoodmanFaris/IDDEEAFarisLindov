package com.example.iddeeafarislindov.screens.persons

import com.example.iddeeafarislindov.data.models.persons.PersonRecord
import com.example.iddeeafarislindov.ui.components.sortmenu.SortOption


data class PersonsUiState(
    val isLoading: Boolean = false,
    val persons: List<PersonRecord> = emptyList(),
    val errorMessage: String? = null,
    val sortOption: SortOption = SortOption.BY_TOTAL_DESC,
    val searchQuery: String = "",
    val entityId: Int = 0,
    val year: String = "",
    val month: String = ""
) {
    val sortedList: List<PersonRecord>
        get() {
            val entityName = when (entityId) {
                1 -> "FEDERACIJA BOSNE I HERCEGOVINE"
                2 -> "REPUBLIKA SRPSKA"
                3 -> "BRČKO DISTRIKT BOSNE I HERCEGOVINE"
                else -> ""
            }

            return persons
                .filter {
                    (entityName.isBlank() || it.entity.equals(entityName, ignoreCase = true)) &&
                            (year.isBlank() || it.year.toString() == year) &&
                            (month.isBlank() || it.month.toString() == month) &&
                            (it.institution.contains(searchQuery.trim(), ignoreCase = true) ||
                                    it.municipality.contains(searchQuery.trim(), ignoreCase = true))
                }
                .let { list ->
                    when (sortOption) {
                        SortOption.BY_TOTAL_DESC -> list.sortedByDescending { it.total }
                        SortOption.BY_TOTAL_ASC -> list.sortedBy { it.total }
                        SortOption.BY_MUNICIPALITY -> list.sortedBy { it.institution }
                    }
                }
        }
}
