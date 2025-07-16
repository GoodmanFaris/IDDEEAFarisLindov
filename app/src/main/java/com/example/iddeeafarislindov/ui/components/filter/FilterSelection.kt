package com.example.iddeeafarislindov.ui.components.filter


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.data.models.died.DiedRequest
import com.example.iddeeafarislindov.data.models.idcard.IdCardsRequest
import com.example.iddeeafarislindov.data.models.persons.PersonsRequest
import com.example.iddeeafarislindov.data.models.vehicles.VehiclesRequest
import com.example.iddeeafarislindov.ui.components.TopBarWithBack
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FilterScreen(
    title: String,
    navController: NavHostController,
    targetRoute: String
) {
    val navy = Color(0xFF0D1B2A)
    var entity by rememberSaveable { mutableStateOf(0) }
    var year by rememberSaveable { mutableStateOf("") }
    var month by rememberSaveable { mutableStateOf("") }



    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = navy
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = navy
                )

                FilterSection(
                    selectedEntity = entity,
                    onEntityChange = { entity = it },
                    year = year,
                    onYearChange = { year = it},
                    month = month,
                    onMonthChange = {
                        if (it.length <= 2 && it.all { c -> c.isDigit() }) {
                            val numeric = it.toIntOrNull()
                            if (numeric == null || (numeric in 1..12)) {
                                month = it
                            }
                        }
                    }

                )

            }

            Button(
                onClick = {
                    val requestJson = when (targetRoute) {
                        "persons_results" -> Gson().toJson(PersonsRequest(entity, 0, 0, year, month))
                        "idcards_results" -> Gson().toJson(IdCardsRequest(entity, 0, 0, year, month))
                        "died_results" -> Gson().toJson(DiedRequest(entity, 0, 0, year, month))
                        "vehicles_results" -> Gson().toJson(VehiclesRequest(entity, 0, 0, year, month))
                        else -> return@Button
                    }
                    val encoded = URLEncoder.encode(requestJson, StandardCharsets.UTF_8.toString())
                    navController.navigate("$targetRoute/$encoded")
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = navy,
                    contentColor = Color.White
                )
            ) {
                Text("Prikaži")
            }
        }
    }
}





