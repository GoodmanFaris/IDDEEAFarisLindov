package com.example.iddeeafarislindov.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.ui.components.TopBar

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val navy = Color(0xFF0D1B2A)
    val backgroundGray = Color(0xFFF5F5F5)

    Scaffold(
        topBar = {
            TopBar(navController = navController)
        },
        floatingActionButton = {
            IconButton(onClick = { navController.navigate("favorites") }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Moji favoriti",
                    tint = navy
                )
            }
        },
        containerColor = backgroundGray
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "InsightBiH",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = navy
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onSearchQueryChanged,
                label = { Text(stringResource(R.string.pretraga_datasetova)) },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = navy,
                    focusedLabelColor = navy,
                    cursorColor = navy
                )
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(uiState.datasetList.filter {
                    it.title.contains(uiState.searchQuery, ignoreCase = true)
                }) { dataset ->
                    DatasetCard(dataset = dataset) {
                        val route = when (dataset.id) {
                            "persons" -> "filter_persons"
                            "died" -> "filter_died"
                            "idcards" -> "filter_idcards"
                            "vehicles" -> "filter_vehicles"
                            else -> dataset.id
                        }
                        navController.navigate(route)
                    }
                }
            }
        }
    }
}
