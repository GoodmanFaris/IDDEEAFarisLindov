package com.example.iddeeafarislindov.screens.DetailScreens.died


import android.content.Intent
import androidx. compose. ui. graphics. Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.data.models.died.DiedRecord
import com.example.iddeeafarislindov.ui.components.DetailRow
import com.example.iddeeafarislindov.ui.components.TopBarWithBack
import com.example.iddeeafarislindov.ui.components.stat.SimplePieChart

@Composable
fun DiedDetailScreen(diedRecord: DiedRecord, navController: NavHostController) {
    val navy = Color(0xFF001F54)
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            DetailRow("Institucija", diedRecord.institution.replace("+", " "))
            DetailRow("Entitet", diedRecord.entity.replace("+", " "))
            DetailRow("Općina", diedRecord.municipality.replace("+", " "))
            DetailRow("Godina", diedRecord.year.toString())
            DetailRow("Mjesec", diedRecord.month.toString())
            DetailRow("Ažurirano", diedRecord.dateUpdate)
            DetailRow("Muškarci", diedRecord.maleTotal.toString())
            DetailRow("Žene", diedRecord.femaleTotal.toString())
            DetailRow("Ukupno", diedRecord.total.toString())


            Spacer(modifier = Modifier.height(16.dp))

            SimplePieChart(
                value1 = diedRecord.maleTotal,
                value2 = diedRecord.femaleTotal,
                title = "Broj umrlih",
                label1 = "Muško",
                label2 = "Žensko"
            )

            val context = LocalContext.current

            Button(
                onClick = {
                    val shareText = """
                Institucija: ${diedRecord.institution.replace("+", " ")}
                Entitet: ${diedRecord.entity.replace("+", " ")}
                Općina: ${diedRecord.municipality.replace("+", " ")}
                Godina: ${diedRecord.year}
                Mjesec: ${diedRecord.month}
                Ažurirano: ${diedRecord.dateUpdate}
                Muškarci: ${diedRecord.maleTotal}
                Žene: ${diedRecord.femaleTotal}
                Ukupno: ${diedRecord.total}
            """.trimIndent()

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        type = "text/plain"
                    }

                    context.startActivity(Intent.createChooser(intent, "Podijeli podatak preko:"))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = navy)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Podijeli",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Podijeli", color = Color.White)
            }

        }
    }
}

