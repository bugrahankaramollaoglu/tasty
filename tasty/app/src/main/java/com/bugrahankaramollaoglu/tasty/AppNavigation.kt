package com.bugrahankaramollaoglu.tasty

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    val startDestination = if (authViewModel.isLoggedIn()) "home" else "login"

    NavHost(navController = navController, startDestination = startDestination) {

        composable("login") {
            LoginScreen(authViewModel, navController) {

            }
        }

        composable("home") {
            HomeScreen(authViewModel, navController)
        }


        composable("sign_in") {
            SignInScreen(authViewModel, navController) { }
        }

        composable("sign_up") {
            SignUpScreen(authViewModel, navController) { }
        }

    }
}
