package ru.gov.rospatent.searchplatform.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.gov.rospatent.searchplatform.models.response.Hit
import ru.gov.rospatent.searchplatform.R

@ExperimentalMaterial3Api
@Composable
fun PatentsList(patents: SnapshotStateList<Hit>, fontFamily: FontFamily, navController: NavController) {
    if (patents.isEmpty()) {
        Box(modifier = Modifier.padding(15.dp)) {
            Text(
                text = stringResource(id = R.string.no_patents), fontFamily = fontFamily
            )
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        itemsIndexed(patents) { index, patent ->
            PatentCard(patent = patent, fontFamily = fontFamily, navController = navController, index = index)
        }
    }
}
