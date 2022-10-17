package ru.gov.rospatent.searchplatform.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.gov.rospatent.searchplatform.R
import ru.gov.rospatent.searchplatform.patents

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(fontFamily: FontFamily, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.app_name_short), fontFamily = fontFamily
                )
            },
            actions = {},
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.ic_filter)
            )
        }
    }, content = { padding ->
        val state = remember { mutableStateOf(TextFieldValue("")) }
        val patents = remember { patents }

        Column(modifier = Modifier.padding(padding)) {
            Box(modifier = Modifier.padding(20.dp, 10.dp)) {
                SearchField(state = state, patents = patents, fontFamily = fontFamily)
            }
            PatentsList(patents = patents, fontFamily = fontFamily, navController = navController)
        }
    })
}
