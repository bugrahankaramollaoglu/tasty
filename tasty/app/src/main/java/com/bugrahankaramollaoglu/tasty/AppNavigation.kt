package com.bugrahankaramollaoglu.tasty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val loginState = authViewModel.loginState

    // Navigate to Home if login succeeds
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(authViewModel = authViewModel, navController = navController)
        }
        composable(Screen.Home.route) {
            // Redirect to login if user is not logged in
            if (authViewModel.loginState !is LoginState.Success) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0)
                    }
                }
            } else {
                HomeScreen(authViewModel)
            }
        }
    }
}
