package ru.gov.rospatent.searchplatform.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailsColumn(
    header: String,
    patentInformation: String,
    fontFamily: FontFamily,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = header, fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 12.sp
        )

        Text(
            text = patentInformation, fontFamily = fontFamily, fontSize = 12.sp
        )
    }

}