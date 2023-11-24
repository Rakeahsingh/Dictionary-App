package com.example.dictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dictionaryapp.core.navigation.Route
import com.example.dictionaryapp.feature_dictionary.presentation.mainScreen.MainScreen
import com.example.dictionaryapp.feature_dictionary.presentation.splashScreen.SplashScreen
import com.example.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                   val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState
                    ) {
                        NavHost(navController = navController, startDestination = Route.SplashScreen){
                            composable(Route.SplashScreen){
                                SplashScreen(onNavigate = {
                                    navController.navigate(it.route)
                                })
                            }

                            composable(Route.MainScreen){
                               MainScreen(
                                   scaffoldState = scaffoldState,
                                   onNavigateUp = { navController.navigateUp() }
                                   )

                            }
                        }
                    }
                }
            }
        }
    }

}

