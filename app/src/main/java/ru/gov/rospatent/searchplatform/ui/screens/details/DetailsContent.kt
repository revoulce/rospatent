package ru.gov.rospatent.searchplatform.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import ru.gov.rospatent.searchplatform.R
import ru.gov.rospatent.searchplatform.models.response.Hit
import ru.gov.rospatent.searchplatform.util.getPatentee

@Composable
fun DetailsContent(patent: Hit, fontFamily: FontFamily) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DetailsColumn(
                header = stringResource(id = R.string.det_application_number),
                patentInformation = patent.common.application.number,
                fontFamily = fontFamily,
                modifier = Modifier.weight(1f)
            )
            DetailsColumn(
                header = stringResource(id = R.string.det_published),
                patentInformation = patent.common.publicationDate,
                fontFamily = fontFamily,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DetailsColumn(
                header = stringResource(id = R.string.det_application_date),
                patentInformation = patent.common.application.filingDate,
                fontFamily = fontFamily,
                modifier = Modifier.weight(1f)
            )
            DetailsColumn(
                header = stringResource(id = R.string.det_ipc),
                patentInformation = patent.common.classification.ipc[0].fullname,
                fontFamily = fontFamily,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DetailsColumn(
                header = stringResource(id = R.string.det_priority_data),
                patentInformation = "${patent.common.application.number}, " + "${patent.common.application.filingDate}, " + " ${
                    patent.common.priority?.get(1)?.number
                }",
                fontFamily = fontFamily,
                modifier = Modifier.weight(1f)
            )
            getPatentee(patent)?.let {
                DetailsColumn(
                    header = stringResource(id = R.string.det_patent_holders),
                    patentInformation = it,
                    fontFamily = fontFamily,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}