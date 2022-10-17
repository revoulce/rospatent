package ru.gov.rospatent.searchplatform.ui.screens.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import ru.gov.rospatent.searchplatform.R
import ru.gov.rospatent.searchplatform.patents
import ru.gov.rospatent.searchplatform.util.getAuthors
import ru.gov.rospatent.searchplatform.util.getCapitalizedTitle
import java.util.*

@ExperimentalMaterial3Api
@Composable
fun PatentDetailsScreen(fontFamily: FontFamily, navController: NavController, index: Int?) {
    require(index != null)

    val patent = patents[index]

    val authors = getAuthors(patent)

    var title = getCapitalizedTitle(patent)

    title = title.lowercase().replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = title,
                fontFamily = fontFamily,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, actions = {}, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.ic_back)
                )
            }
        })
    }) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Details(
                patent = patent,
                title = title,
                inventors = authors,
                fontFamily = fontFamily
            )

        }
    }
}
