package com.example.iddeeafarislindov.screens.favorites.favoritevehicles


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.App
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.screens.vehicles.VehicleRecordCard
import com.example.iddeeafarislindov.screens.vehicles.VehiclesViewModel
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FavoriteVehiclesScreen(
    navController: NavHostController,
    viewModel: VehiclesViewModel
) {
    val favorites by viewModel.favoriteVehicles.collectAsState(initial = emptyList())
    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.sa_uvana_registrovana_vozila), fontSize = 22.sp)
            Spacer(modifier = Modifier.height(12.dp))

            if (favorites.isEmpty()) {
                Text(stringResource(R.string.nemate_sa_uvanih_vozila))
            } else {
                LazyColumn {
                    items(favorites) { item ->
                        VehicleRecordCard(
                            record = item,
                            navController = navController,
                            viewModel = viewModel,
                            isFavoriteOverride = true,
                            onUnfavorite = { viewModel.removeFromFavorites(item) }
                        )
                    }
                }
            }

        }
    }
}


