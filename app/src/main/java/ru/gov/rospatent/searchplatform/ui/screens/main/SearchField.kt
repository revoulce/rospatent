package ru.gov.rospatent.searchplatform.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ru.gov.rospatent.searchplatform.R
import ru.gov.rospatent.searchplatform.models.response.Hit

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun SearchField(
    state: MutableState<TextFieldValue>, patents: SnapshotStateList<Hit>, fontFamily: FontFamily
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { value -> state.value = value },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_field_desc)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = {
                    state.value = TextFieldValue("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.search_field_clear)
                    )
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            searchPatents(
                searchString = state.value.text, result = patents
            )
        }),
        textStyle = TextStyle(fontFamily = fontFamily),
        shape = RoundedCornerShape(10.dp)
    )
}


