package ru.gov.rospatent.searchplatform.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailsHeader(title: String, inventors: String?, fontFamily: FontFamily) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title, fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        Text(
            text = "Авторы: $inventors",
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}