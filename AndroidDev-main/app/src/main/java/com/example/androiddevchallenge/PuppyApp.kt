package com.example.androiddevchallenge

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalMaterialApi
@Composable
fun PuppyApp() {
    val navController = rememberNavController()
    MaterialTheme {
        NavHost(navController, startDestination = "home") {
            composable("home") { Home(navController) }
            composable(
                "detail/{name}"
            ) { it ->
                PuppyDetail(
                    it.arguments?.getString("name")!!,
                    navController
                )
            }
        }
    }
}
