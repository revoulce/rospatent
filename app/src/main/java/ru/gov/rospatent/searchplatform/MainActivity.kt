package ru.gov.rospatent.searchplatform

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gov.rospatent.searchplatform.api.RetrofitApi
import ru.gov.rospatent.searchplatform.models.SearchRequest
import ru.gov.rospatent.searchplatform.models.SearchResponse
import ru.gov.rospatent.searchplatform.models.response.Hit
import ru.gov.rospatent.searchplatform.ui.theme.RospatentTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE)
        sharedPreferences.edit().putString("token", "26a213594e7f4f6e8cd89064d885ea93").apply()

        val montserratFontFamily = FontFamily(
            Font(R.font.montserrat_thin, FontWeight.Thin),
            Font(R.font.montserrat_thinitalic, FontWeight.Thin, FontStyle.Italic),
            Font(R.font.montserrat_extralight, FontWeight.ExtraLight),
            Font(R.font.montserrat_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
            Font(R.font.montserrat_light, FontWeight.Light),
            Font(R.font.montserrat_lightitalic, FontWeight.Light, FontStyle.Italic),
            Font(R.font.montserrat_regular, FontWeight.Normal),
            Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
            Font(R.font.montserrat_medium, FontWeight.Medium),
            Font(R.font.montserrat_mediumitalic, FontWeight.Medium, FontStyle.Italic),
            Font(R.font.montserrat_semibold, FontWeight.SemiBold),
            Font(R.font.montserrat_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
            Font(R.font.montserrat_bold, FontWeight.Bold),
            Font(R.font.montserrat_bolditalic, FontWeight.Bold, FontStyle.Italic),
            Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),
            Font(R.font.montserrat_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
            Font(R.font.montserrat_black, FontWeight.Black),
            Font(R.font.montserrat_blackitalic, FontWeight.Black, FontStyle.Italic),
        )

        setContent {
            RospatentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
                ) {
                    App(fontFamily = montserratFontFamily)
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun App(fontFamily: FontFamily) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(id = R.string.app_name_short), fontFamily = fontFamily
            )
        }, actions = {})
    }, content = { padding ->
        val state = remember { mutableStateOf(TextFieldValue("")) }
        val patents = remember { mutableStateListOf<Hit>() }

        Column(modifier = Modifier.padding(padding)) {
            Box(modifier = Modifier.padding(20.dp, 10.dp)) {
                SearchField(state = state, patents = patents, fontFamily = fontFamily)
            }
            PatentsList(patents = patents, fontFamily = fontFamily)
        }
    })
}

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

fun searchPatents(
    searchString: String, result: SnapshotStateList<Hit>
) {
    val url = "https://searchplatform.rospatent.gov.ru/patsearch/v0.2/"

    val retrofit =
        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

    val retrofitApi = retrofit.create(RetrofitApi::class.java)
    val searchRequest = SearchRequest(searchString)
    val call: Call<SearchResponse?>? = retrofitApi.postSearch(searchRequest)

    call!!.enqueue(object : Callback<SearchResponse?> {
        override fun onResponse(
            call: Call<SearchResponse?>, response: Response<SearchResponse?>
        ) {
            val model: SearchResponse? = response.body()

            val resp = "Response Code: " + response.code() + "\nAvailable: " + (model?.available
                ?: "Error") + "\nTotal: " + (model?.total ?: "Error")
            Log.i("API", resp)

            if (model != null) {
                result.addAll(model.hits)
            }
        }

        override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
            t.message?.let { Log.e("API", it) }
        }
    })
}

@Composable
fun PatentsList(patents: SnapshotStateList<Hit>, fontFamily: FontFamily) {
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
        items(patents) { patent ->
            Box {
                Text(
                    text = patent.snippet.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily
                )
            }
        }
    }
}