package com.example.iddeeafarislindov.screens.favorites.favoriteidcards


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
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.screens.idcards.IdCardRecordCard
import com.example.iddeeafarislindov.screens.idcards.IdCardsViewModel
import com.example.iddeeafarislindov.screens.idcards.IdCardsViewModelFactory
import com.example.iddeeafarislindov.ui.components.TopBarWithBack
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FavoriteIdCardsScreen(
    navController: NavHostController,
    viewModel: IdCardsViewModel
) {
    val context = LocalContext.current
    val db = (context.applicationContext as App).database
    val viewModel: IdCardsViewModel = viewModel(factory = IdCardsViewModelFactory(db))

    val favorites by viewModel.favoriteIdCards.collectAsState(initial = emptyList())
    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.sa_uvane_li_ne_karte), fontSize = 22.sp)
            Spacer(modifier = Modifier.height(12.dp))

            if (favorites.isEmpty()) {
                Text(stringResource(R.string.nemate_sa_uvanih_li_nih_karata))
            } else {
                LazyColumn {
                    items(favorites) { record ->
                        IdCardRecordCard(
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


