package ru.gov.rospatent.searchplatform.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ireward.htmlcompose.HtmlText
import ru.gov.rospatent.searchplatform.R
import ru.gov.rospatent.searchplatform.models.response.Hit

@Composable
fun Details(
    patent: Hit, title: String, inventors: String?, fontFamily: FontFamily
) {
    LazyColumn(
        modifier = Modifier, verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Surface() {
                DetailsHeader(title = title, inventors = inventors, fontFamily = fontFamily)
            }
        }

        item {
            Divider(thickness = 15.dp)
        }

        item {
            Surface {
                DetailsContent(patent = patent, fontFamily = fontFamily)
            }
        }

        item {
            Divider(thickness = 15.dp)
        }

        item {
            Surface() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.det_description),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    HtmlText(
                        text = patent.snippet.description,
                        style = TextStyle(
                            fontFamily = fontFamily,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }
        }
    }
}