package ru.gov.rospatent.searchplatform.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.gov.rospatent.searchplatform.R
import ru.gov.rospatent.searchplatform.models.response.Hit
import ru.gov.rospatent.searchplatform.util.getAuthors
import ru.gov.rospatent.searchplatform.util.getCapitalizedTitle
import java.util.*

@ExperimentalMaterial3Api
@Composable
fun PatentCard(patent: Hit, fontFamily: FontFamily, navController: NavController, index: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), onClick = {
        navController.navigate("details/$index")
    }) {
        val authors = getAuthors(patent)
        var title = getCapitalizedTitle(patent)

        title = title.lowercase().replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }

        Row {
            Box(modifier = Modifier.padding(15.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_document),
                    contentDescription = stringResource(id = R.string.ic_document)
                )
            }

            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title.replace("<em>", "").replace("</em>", ""),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                )

                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    authors?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = fontFamily,
                            fontSize = 12.sp,
                            softWrap = true,
                            modifier = Modifier.width(70.dp)
                        )
                    }

                    patent.common.priority?.get(0)?.let {
                        Text(
                            text = it.number,
                            maxLines = 1,
                            fontFamily = fontFamily,
                            fontSize = 12.sp
                        )
                    }

                    patent.snippet.classification.ipc?.let {
                        Text(
                            text = it, fontFamily = fontFamily, fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}