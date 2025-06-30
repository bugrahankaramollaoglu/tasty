package com.bugrahankaramollaoglu.tasty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.CourierScreen
import com.bugrahankaramollaoglu.tasty.view.app_screens.DetailsScreen
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

        composable(
            route = "details/{foodId}",
            arguments = listOf(navArgument("foodId") {
                type = NavType.IntType  // <-- Important: specify argument type as Int
            })
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getInt("foodId") ?: 0
            DetailsScreen(foodId = foodId, navController = navController)
        }

        composable("home") {
            HomeScreen(authViewModel, navController)
        }

        composable("courier") {
            CourierScreen(navController)
        }
    }
}
