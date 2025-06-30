package com.bugrahankaramollaoglu.tasty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.CourierScreen
import com.bugrahankaramollaoglu.tasty.view.app_screens.HomeScreen
import com.bugrahankaramollaoglu.tasty.view.login_screens.LoginScreen
import com.bugrahankaramollaoglu.tasty.view.login_screens.SignInScreen
import com.bugrahankaramollaoglu.tasty.view.login_screens.SignUpScreen
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel

// page routingleri düzenleyen fonksiyon
@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    val startDestination = if (authViewModel.isLoggedIn()) "home" else "login"

    NavHost(navController = navController, startDestination = startDestination) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("sign_in") {
            SignInScreen(
                authViewModel = authViewModel,
                navController = navController,
            ) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        composable("sign_up") {
            SignUpScreen(
                viewModel = authViewModel,
                navController = navController,
            ) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        composable("home") {
            HomeScreen(authViewModel, navController)
        }

        composable("courier") {
            CourierScreen(navController)
        }
    }
}
