package com.bugrahankaramollaoglu.tasty

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(authViewModel: AuthViewModel, navController: NavHostController) {

}


/* Button(onClick = {
            authViewModel.logout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Logout")
        }  */