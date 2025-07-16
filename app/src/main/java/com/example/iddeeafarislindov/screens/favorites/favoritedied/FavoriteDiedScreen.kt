package com.example.iddeeafarislindov.screens.favorites.favoritedied


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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.screens.died.DiedRecordCard
import com.example.iddeeafarislindov.screens.died.DiedViewModel
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

@Composable
fun FavoriteDiedScreen(
    navController: NavHostController,
    viewModel: DiedViewModel
) {
    val favorites by viewModel.favoriteDied.collectAsState(initial = emptyList())
    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.sa_uvani_podaci_o_umrlima), fontSize = 22.sp)
            Spacer(modifier = Modifier.height(12.dp))

            if (favorites.isEmpty()) {
                Text(stringResource(R.string.nemate_sa_uvanih_zapisa))
            } else {
                LazyColumn {
                    items(favorites) { record ->
                        DiedRecordCard(
                            record = record,
                            navController = navController,
                            viewModel = viewModel,
                            isFavoriteOverride = true,
                            onUnfavorite = { viewModel.removeFromFavorites(record) }
                        )
                    }
                }
            }
        }
    }
}


