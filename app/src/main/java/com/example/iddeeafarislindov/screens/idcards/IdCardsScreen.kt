package com.example.iddeeafarislindov.screens.idcards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.App
import com.example.iddeeafarislindov.data.models.idcard.IdCardsRequest
import com.example.iddeeafarislindov.ui.components.sortmenu.DropdownSortMenu
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.ui.components.HandleUiState
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

@Composable
fun IdCardsScreen(
request: IdCardsRequest,
navController: NavHostController
) {
    val context = LocalContext.current
    val db = (context.applicationContext as App).database
    val viewModel: IdCardsViewModel = viewModel(factory = IdCardsViewModelFactory(db))

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(request) {
        viewModel.loadIdCards(
            entityId = request.entityId,
            year = request.year,
            month = request.month
        )
    }
    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->



        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)) {
            val navy = Color(0xFF0D1B2A)

            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                label = { Text(stringResource(R.string.pretraga)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = navy,
                    unfocusedBorderColor = navy,
                    cursorColor = navy,
                    focusedLabelColor = navy,
                    unfocusedLabelColor = navy,
                    focusedTextColor = navy,
                    unfocusedTextColor = navy
                )
            )




            Spacer(Modifier.height(12.dp))



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                    items(list) { cards ->
                        IdCardRecordCard(
                            record = cards,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun IdCardRecordCard(
    record: IssuedIdCard,
    navController: NavHostController,
    viewModel: IdCardsViewModel,
    isFavoriteOverride: Boolean? = null,
    onUnfavorite: (() -> Unit)? = null
) {
    val navy = Color(0xFF0D1B2A)
    val green = Color(0xFF3E9642)
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
                navController.navigate("idcards_details/$json")
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
                        .background(green)
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


