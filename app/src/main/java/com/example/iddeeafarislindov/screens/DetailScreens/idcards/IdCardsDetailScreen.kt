package com.example.iddeeafarislindov.screens.DetailScreens.idcards

import android.content.Intent
import androidx. compose. ui. graphics. Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.navigation.NavController
import com.example.iddeeafarislindov.data.models.idcard.IssuedIdCard
import com.example.iddeeafarislindov.ui.components.DetailRow
import com.example.iddeeafarislindov.ui.components.TopBarWithBack
import com.example.iddeeafarislindov.ui.components.stat.SimplePieChart

@Composable
fun IdCardsDetailScreen(record: IssuedIdCard, navController: NavController) {
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
            Text(
                text = "Detalji o izdavanju ličnih karata",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = navy,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(bottom = 16.dp))

            DetailRow("Institucija", record.institution.replace("+", " "))
            DetailRow("Entitet", record.entity.replace("+", " "))
            DetailRow("Kanton", record.canton?.replace("+", " ") ?: "N/A")
            DetailRow("Općina", record.municipality.replace("+", " "))
            DetailRow("Godina", record.year.toString())
            DetailRow("Mjesec", record.month.toString())
            DetailRow("Ažurirano", record.dateUpdate)
            DetailRow("Prvi put (Muškarci)", record.issuedFirstTimeMaleTotal.toString())
            DetailRow("Zamjena (Muškarci)", record.replacedMaleTotal.toString())
            DetailRow("Prvi put (Žene)", record.issuedFirstTimeFemaleTotal.toString())
            DetailRow("Zamjena (Žene)", record.replacedFemaleTotal.toString())
            DetailRow("Ukupno", record.total.toString())

            Spacer(modifier = Modifier.height(16.dp))

            SimplePieChart(
                title = "Prvi put izdate lične karte",
                label1 = "Muško",
                value1 = record.issuedFirstTimeMaleTotal,
                label2 = "Žensko",
                value2 = record.issuedFirstTimeFemaleTotal
            )

            Spacer(modifier = Modifier.height(16.dp))

            SimplePieChart(
                title = "Zamijenjene lične karte",
                label1 = "Muško",
                value1 = record.replacedMaleTotal,
                label2 = "Žensko",
                value2 = record.replacedFemaleTotal
            )

            Button(
                onClick = {
                    val shareText = """
                        Institucija: ${record.institution.replace("+", " ")}
                        Entitet: ${record.entity.replace("+", " ")}
                        Kanton: ${record.canton?.replace("+", " ") ?: "N/A"}
                        Općina: ${record.municipality}
                        Godina: ${record.year}
                        Mjesec: ${record.month}
                        Ažurirano: ${record.dateUpdate}
                        Prvi put (Muškarci): ${record.issuedFirstTimeMaleTotal}
                        Zamjena (Muškarci): ${record.replacedMaleTotal}
                        Prvi put (Žene): ${record.issuedFirstTimeFemaleTotal}
                        Zamjena (Žene): ${record.replacedFemaleTotal}
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