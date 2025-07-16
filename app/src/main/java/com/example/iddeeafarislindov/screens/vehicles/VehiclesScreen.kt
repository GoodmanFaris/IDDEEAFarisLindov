package com.example.iddeeafarislindov.screens.vehicles


import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.App
import com.example.iddeeafarislindov.data.models.vehicles.VehiclesRequest
import com.example.iddeeafarislindov.ui.components.sortmenu.DropdownSortMenu
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.ui.components.HandleUiState
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

@Composable
fun VehiclesScreen(
    navController: NavHostController,
    request: VehiclesRequest
) {
    val context = LocalContext.current
    val db = (context.applicationContext as App).database
    val viewModel: VehiclesViewModel = viewModel(factory = VehicleViewModelFactory(db))


    LaunchedEffect(request) {
        viewModel.loadVehicles(
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
                dataList = uiState.sortedList
            ) { list ->
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(list) { item ->
                        VehicleRecordCard(
                            record = item,
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
fun VehicleRecordCard(
    record: RegisteredVehicle,
    navController: NavHostController,
    viewModel: VehiclesViewModel,
    isFavoriteOverride: Boolean? = null,
    onUnfavorite: (() -> Unit)? = null
) {
    val darkBlue = Color(0xFF0D1B2A)
    val yellow = Color(0xFFD08006)
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
                navController.navigate("vehicles_details/$json")
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
                        .background(yellow)
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = record.registrationPlace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = darkBlue
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(R.string.ukupno) + "${record.total}",
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
