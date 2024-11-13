package com.example.harrypotter.presentation.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.harrypotter.presentation.ui.screens.CharacterListScreen
import com.example.harrypotter.presentation.ui.screens.CharacterScreen
import com.example.harrypotter.presentation.ui.screens.ChooseOptionScreen
import com.example.harrypotter.presentation.ui.screens.SpellListScreen
import com.example.harrypotter.util.ScreenUtils.CHARACTER_SCREEN
import com.example.harrypotter.util.ScreenUtils.CHARACTER_LIST_SCREEN
import com.example.harrypotter.util.ScreenUtils.SPELL_LIST_SCREEN
import com.example.harrypotter.util.ScreenUtils.CHOOSE_OPTION_SCREEN

sealed class Screen(val route: String) {
    data object CharacterListScreen : Screen(route = CHARACTER_LIST_SCREEN)
    data object CharacterScreen : Screen(route = CHARACTER_SCREEN)
    data object ChooseOptionScreen : Screen(route = CHOOSE_OPTION_SCREEN)
    data object SpellListScreen : Screen(route = SPELL_LIST_SCREEN)
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ChooseOptionScreen.route,
        builder = {
            composable(route = Screen.CharacterListScreen.route) {
                CharacterListScreen(navController = navController)
            }
            composable(
                route = "${Screen.CharacterScreen.route}/{characterID}",
                arguments = listOf(navArgument("characterID") {
                    type = NavType.StringType
                })
            ) {
                val characterID = it.arguments?.getString("characterID").orEmpty()
                CharacterScreen(characterID = characterID)
            }
            composable(route = Screen.SpellListScreen.route) {
                SpellListScreen()
            }
            composable(route = Screen.ChooseOptionScreen.route) {
                ChooseOptionScreen(navController = navController)
            }
        })
}