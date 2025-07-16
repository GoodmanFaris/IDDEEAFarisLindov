package com.example.iddeeafarislindov.ui.components.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

@Composable
fun <T> FavoriteListScreen(
    navController: NavHostController,
    title: String,
    emptyMessage: String,
    favorites: List<T>,
    cardContent: @Composable (T) -> Unit
) {
    Scaffold(topBar = { TopBarWithBack(navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(title, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(12.dp))

            if (favorites.isEmpty()) {
                Text(emptyMessage)
            } else {
                LazyColumn {
                    items(favorites) { item ->
                        cardContent(item)
                    }
                }
            }
        }
    }
}
