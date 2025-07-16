package com.example.iddeeafarislindov.screens.about


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iddeeafarislindov.R
import com.example.iddeeafarislindov.ui.components.TopBarWithBack

@Composable
fun AboutScreen(navController: NavHostController) {
    val navy = Color(0xFF0D1B2A)

    Scaffold(
        topBar = { TopBarWithBack(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column {
                Text(
                    text = stringResource(R.string.o_aplikaciji),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = navy
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.opis_apk),
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.glavne_funkcionalnosti_uklju_uju),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = navy
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(stringResource(R.string.filtriranje_i_sortiranje_podataka), color = Color.DarkGray)
                Text(stringResource(R.string.pregled_rezultata_po_op_inama_i_institucijama), color = Color.DarkGray)
                Text(stringResource(R.string.dodavanje_zapisa_u_favorite), color = Color.DarkGray)
                Text(stringResource(R.string.prikaz_statistika_i_grafikona), color = Color.DarkGray)
            }
        }
    }
}
