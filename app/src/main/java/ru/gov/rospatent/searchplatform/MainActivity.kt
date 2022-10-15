package ru.gov.rospatent.searchplatform

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import ru.gov.rospatent.searchplatform.ui.theme.RospatentTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RospatentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
                ) {
                    App()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun App() {
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(id = R.string.app_name_short)) }, actions = {})
    }, content = { padding ->
        val state = remember { mutableStateOf(TextFieldValue("")) }
        Box(modifier = Modifier.padding(padding)) {
            SearchField(state = state)
        }
    })
}

@ExperimentalMaterial3Api
@Composable
fun SearchField(state: MutableState<TextFieldValue>) {
    TextField(value = state.value, onValueChange = { value ->
        state.value = value
    }, modifier = Modifier.fillMaxWidth(), leadingIcon = {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.search_field_desc)
        )
    }, trailingIcon = {
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
    }, singleLine = true, colors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.surface,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AppPreview() {
    RospatentTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            App()
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SearchFieldPreview() {
    RospatentTheme {
        Surface {
            val state = remember { mutableStateOf(TextFieldValue("Ok")) }
            SearchField(state = state)
        }
    }
}