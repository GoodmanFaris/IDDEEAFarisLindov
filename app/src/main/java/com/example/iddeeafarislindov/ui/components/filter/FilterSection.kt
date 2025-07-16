package com.example.iddeeafarislindov.ui.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iddeeafarislindov.R

@Composable
fun FilterSection(
    selectedEntity: Int,
    onEntityChange: (Int) -> Unit,
    year: String,
    onYearChange: (String) -> Unit,
    month: String,
    onMonthChange: (String) -> Unit
) {
    val navy = Color(0xFF0D1B2A)

    Column {
        Text(
            text = stringResource(R.string.entitet),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = navy
        )

        Spacer(Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RadioOption("Svi", selected = selectedEntity == 0, onClick = { onEntityChange(0) }, navy)
            RadioOption("FBiH", selected = selectedEntity == 1, onClick = { onEntityChange(1) }, navy)
            RadioOption("RS", selected = selectedEntity == 2, onClick = { onEntityChange(2) }, navy)
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = year,
            onValueChange = onYearChange,
            label = { Text(stringResource(R.string.godina)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = navy,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = navy,
                unfocusedLabelColor = Color.Gray,
                cursorColor = navy,
                focusedTextColor = navy,
                unfocusedTextColor = Color.Black
            )
        )


        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = month,
            onValueChange = onMonthChange,
            label = { Text(stringResource(R.string.mjesec)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = navy,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = navy,
                unfocusedLabelColor = Color.Gray,
                cursorColor = navy,
                focusedTextColor = navy,
                unfocusedTextColor = Color.Black
            )
        )
    }
}
