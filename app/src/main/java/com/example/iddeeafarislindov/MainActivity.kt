package com.example.iddeeafarislindov

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.iddeeafarislindov.navigation.AppNavHost
import com.example.iddeeafarislindov.screens.main.DatasetCard
import com.example.iddeeafarislindov.screens.main.DatasetItem
import com.example.iddeeafarislindov.ui.theme.IDDEEAFarisLindovTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavHost()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDatasetCard() {
    IDDEEAFarisLindovTheme {
        DatasetCard(
            dataset = DatasetItem(
                title = "Broj novorođenih po mjesecima",
                description = "Statistika broja novorođenih za svaku općinu po mjesecima i godinama",
                id = "probni"
            ),
            onClick = {}

        )
    }
}