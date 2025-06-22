package com.bugrahankaramollaoglu.tasty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun LoginScreen(
    authViewModel: AuthViewModel, navController: NavHostController
) {
    val loginState = authViewModel.loginState
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            authViewModel.login(username, password)
        }) {
            Text("Login")
        }

        when (loginState) {
            is LoginState.Loading -> Text("Loading...")
            is LoginState.Error -> Text("Error: ${loginState.error}")
            else -> {}
        }
    }
}


@Composable
fun HomeScreen(authViewModel: AuthViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome Home, ${authViewModel.loggedInUsername}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authViewModel.logout()
        }) {
            Text("Logout")
        }
    }
}
