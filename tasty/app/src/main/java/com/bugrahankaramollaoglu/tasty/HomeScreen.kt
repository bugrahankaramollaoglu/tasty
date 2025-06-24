package com.bugrahankaramollaoglu.tasty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bugrahankaramollaoglu.tasty.util.CustomColors

@Composable
fun HomeScreen(authViewModel: AuthViewModel, navController: NavHostController) {


    val name = authViewModel.loggedInUsername

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomYellow)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome: $name")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }, modifier = Modifier.width(100.dp) // Adjust width as needed
            ) {
                Text("Logout")
            }
        }
    }
}


/* Button(onClick = {
            authViewModel.logout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Logout")
        }  */