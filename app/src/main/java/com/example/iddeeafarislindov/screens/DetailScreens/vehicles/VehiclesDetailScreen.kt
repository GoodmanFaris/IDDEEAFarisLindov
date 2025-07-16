package com.example.iddeeafarislindov.screens.DetailScreens.vehicles

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.iddeeafarislindov.data.models.vehicles.RegisteredVehicle
import com.example.iddeeafarislindov.ui.components.DetailRow
import com.example.iddeeafarislindov.ui.components.TopBarWithBack
import com.example.iddeeafarislindov.ui.components.stat.SimplePieChart
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color


@Composable
fun VehiclesDetailScreen(record: RegisteredVehicle, navController: NavController) {
    val navy = Color(0xFF001F54)
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            DetailRow("Entitet", record.entity.replace("+", " "))
            DetailRow("Kanton", record.canton.replace("+", " "))
            DetailRow("Općina", record.municipality.replace("+", " "))
            DetailRow("Mjesto registracije", record.registrationPlace)
            DetailRow("Godina", record.year.toString())
            DetailRow("Mjesec", record.month.toString())
            DetailRow("Datum ažuriranja", record.dateUpdate)
            DetailRow("Domaća vozila", record.totalDomestic.toString())
            DetailRow("Strana vozila", record.totalForeign.toString())
            DetailRow("Ukupno", record.total.toString())

            Spacer(Modifier.height(24.dp))

            SimplePieChart(
                value1 = record.totalDomestic,
                value2 = record.totalForeign,
                title = "Porijeklo uvoza",
                label1 = "Domaće",
                label2 = "Strano"
            )

            Button(
                onClick = {
                    val shareText = """
                        Entitet: ${record.entity.replace("+", " ")}
                        Kanton: ${record.canton.replace("+", " ")}
                        Općina: ${record.municipality.replace("+", " ")}
                        Mjesto registracije: ${record.registrationPlace}
                        Godina: ${record.year}
                        Mjesec: ${record.month}
                        Datum ažuriranja: ${record.dateUpdate}
                        Domaća vozila: ${record.totalDomestic}
                        Strana vozila: ${record.totalForeign}
                        Ukupno: ${record.total}
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
