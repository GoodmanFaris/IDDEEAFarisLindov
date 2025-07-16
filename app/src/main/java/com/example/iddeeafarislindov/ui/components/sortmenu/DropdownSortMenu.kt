package com.example.iddeeafarislindov.ui.components.sortmenu


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class SortOption {
    BY_TOTAL_DESC,
    BY_TOTAL_ASC,
    BY_MUNICIPALITY
}

@Composable
fun DropdownSortMenu(
    selectedOption: SortOption,
    onOptionSelected: (SortOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        Text(
            text = when (selectedOption) {
                SortOption.BY_TOTAL_DESC -> "Najviše ukupno"
                SortOption.BY_TOTAL_ASC -> "Najmanje ukupno"
                SortOption.BY_MUNICIPALITY -> "Po opštini"
            },
            modifier = Modifier
                .clickable { expanded = true }
                .padding(8.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Najviše ukupno") },
                onClick = {
                    onOptionSelected(SortOption.BY_TOTAL_DESC)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Najmanje ukupno") },
                onClick = {
                    onOptionSelected(SortOption.BY_TOTAL_ASC)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Po opštini") },
                onClick = {
                    onOptionSelected(SortOption.BY_MUNICIPALITY)
                    expanded = false
                }
            )
        }
    }
}