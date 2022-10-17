package ru.gov.rospatent.searchplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.gov.rospatent.searchplatform.models.response.Hit
import ru.gov.rospatent.searchplatform.ui.screens.details.PatentDetailsScreen
import ru.gov.rospatent.searchplatform.ui.screens.main.MainScreen
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
//                    MainScreen(fontFamily = montserratFontFamily, navController = navController)
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController, startDestination = "home"
                    ) {
                        composable(route = "home") {
                            MainScreen(fontFamily = montserratFontFamily, navController = navController)
                        }

                        composable(
                            route = "details/{index}",
                            arguments = listOf(
                                navArgument("index") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            PatentDetailsScreen(fontFamily = montserratFontFamily, navController = navController, index = it.arguments?.getInt("index"))
                        }
                    }
                }
            }
        }
    }
}

var patents = mutableStateListOf<Hit>()