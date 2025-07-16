package com.example.iddeeafarislindov.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.screens.main.DatasetCard
import com.example.iddeeafarislindov.screens.main.DatasetItem
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

@Composable
fun FavoritesScreen(navController: NavHostController) {
    data class LocalDatasetItem(val title: String, val description: String, val onClick: () -> Unit)

    val items = listOf(
        LocalDatasetItem(
            title = stringResource(R.string.sacuvane_osobe),
            description = stringResource(R.string.pregled_sa_uvanih_osoba),
            onClick = { navController.navigate("favorites/persons") }
        ),
        LocalDatasetItem(
            title = stringResource(R.string.sacuvane_li_ne_karte),
            description = stringResource(R.string.pregled_sa_uvanih_li_nih_karata),
            onClick = { navController.navigate("favorites/idcards") }
        ),
        LocalDatasetItem(
            title = stringResource(R.string.sacvani_umrli),
            description = stringResource(R.string.pregled_sa_uvanih_zapisa_o_umrlim_osobama),
            onClick = { navController.navigate("favorites/died") }
        ),
        LocalDatasetItem(
            title = stringResource(R.string.sacuvana_vozila),
            description = stringResource(R.string.pregled_sa_uvanih_vozila),
            onClick = { navController.navigate("favorites/vehicles") }
        )
    )

    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Moji favoriti",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            items.forEach { item ->
                DatasetCard(
                    dataset = DatasetItem(
                        title = item.title,
                        description = item.description,
                        id = ""
                    ),
                    onClick = item.onClick
                )
            }
        }
    }
}
