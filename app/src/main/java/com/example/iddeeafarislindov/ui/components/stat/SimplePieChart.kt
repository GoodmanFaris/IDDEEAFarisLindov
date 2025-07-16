package com.example.iddeeafarislindov.ui.components.stat


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun SimplePieChart(
    title: String,
    label1: String,
    value1: Int,
    label2: String,
    value2: Int,
    modifier: Modifier = Modifier
) {
    val total = (value1 + value2).takeIf { it > 0 } ?: 1
    val angle1 = 360f * value1 / total
    val angle2 = 360f * value2 / total

    val color1 = Color(0xFF2196F3) // plava
    val color2 = Color(0xFF4CAF50) // zelena

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Canvas(
                modifier = Modifier
                    .size(180.dp)
                    .padding(8.dp)
            ) {
                drawArc(
                    color = color1,
                    startAngle = 0f,
                    sweepAngle = angle1,
                    useCenter = true
                )
                drawArc(
                    color = color2,
                    startAngle = angle1,
                    sweepAngle = angle2,
                    useCenter = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Legenda
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendRow(color = color1, label = "$label1: $value1")
                LegendRow(color = color2, label = "$label2: $value2")
            }
        }
    }
}

@Composable
fun LegendRow(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(12.dp)) {
            drawCircle(color)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, fontSize = 14.sp)
    }
}
