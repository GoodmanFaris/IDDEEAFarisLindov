package com.example.iddeeafarislindov.screens.persons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.App
import com.example.iddeeafarislindov.data.models.persons.PersonsRequest
import com.example.iddeeafarislindov.ui.components.sortmenu.DropdownSortMenu
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.data.models.persons.PersonRecord
import com.example.iddeeafarislindov.ui.components.HandleUiState
import com.example.iddeeafarislindov.ui.components.TopBarWithBack
import kotlinx.coroutines.launch

@Composable
fun PersonsScreen(
    request: PersonsRequest,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val db = (context.applicationContext as App).database
    val viewModel: PersonsViewModel = viewModel(factory = PersonsViewModelFactory(db))


    LaunchedEffect(request) {
        viewModel.loadPersons(
            entityId = request.entityId,
            year = request.year,
            month = request.month
        )
    }
    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            val uiState by viewModel.uiState.collectAsState()

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                label = { Text(stringResource(R.string.pretraga)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0D1B2A),
                    unfocusedBorderColor = Color(0xFF0D1B2A),
                    cursorColor = Color(0xFF0D1B2A),
                    focusedLabelColor = Color(0xFF0D1B2A),
                    unfocusedLabelColor = Color(0xFF0D1B2A),
                    focusedTextColor = Color(0xFF0D1B2A),
                    unfocusedTextColor = Color(0xFF0D1B2A)
                )
            )

            Spacer(Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(stringResource(R.string.sortiraj_po))

            DropdownSortMenu(
                selectedOption = uiState.sortOption,
                onOptionSelected = { viewModel.setSortOption(it) }
            )
        }
        Spacer(Modifier.height(8.dp))

            HandleUiState(
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                dataList = uiState.sortedList,
                emptyMessage = stringResource(R.string.nema_rezultata_za_tra_ene_filtere)
            ) { list ->
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(list) { person ->
                        PersonRecordCard(
                            record = person,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
}}

@Composable
fun PersonRecordCard(
    record: PersonRecord,
    navController: NavHostController,
    viewModel: PersonsViewModel,
    isFavoriteOverride: Boolean? = null,
    onUnfavorite: (() -> Unit)? = null
) {
    val navy = Color(0xFF0D1B2A)
    val blue = Color(0xFF0570A1)
    val grayText = Color(0xFF555555)

    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(record) {
        isFavorite = isFavoriteOverride ?: viewModel.isFavorite(record)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                val json = URLEncoder.encode(
                    Gson().toJson(record),
                    StandardCharsets.UTF_8.toString()
                )
                navController.navigate("persons_details/$json")
            },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.height(IntrinsicSize.Min)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .fillMaxHeight()
                        .background(blue)
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = record.institution,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = navy
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(R.string.ukupno) +" ${record.total}",
                        fontSize = 14.sp,
                        color = grayText
                    )
                }

                IconToggleButton(
                    checked = isFavorite,
                    onCheckedChange = {
                        isFavorite = !isFavorite
                        if (!isFavorite && onUnfavorite != null) {
                            onUnfavorite()
                        } else {
                            viewModel.addToFavorites(record)
                        }
                    },
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Favorit",
                        tint = if (isFavorite) Color(0xFFFFC107) else Color.Gray
                    )
                }
            }
        }
    }
}



