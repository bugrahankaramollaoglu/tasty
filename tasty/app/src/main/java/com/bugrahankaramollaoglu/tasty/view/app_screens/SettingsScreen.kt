package com.bugrahankaramollaoglu.tasty.view.app_screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel

@Composable
fun SettingsScreen(authViewModel: AuthViewModel, navController: NavController) {

    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {


        Button(
            modifier = Modifier.align(Alignment.Center), onClick = {
                Toast.makeText(context, "You have signed out.", Toast.LENGTH_SHORT).show()
                authViewModel.logout()
                navController.navigate("login") {

                    // popUpTo(0) clears ALL stacks
                    // popUpTo("home") clears the stack
                    // back up to the "home". if it does not "home"
                    // in its stack, it behaves unpredictably
                    popUpTo(0) { inclusive = true }

                    // eger stack'in son elemanı ayniysa, 2. kere
                    // stacke eklemesini önler
                    launchSingleTop = true

                }

            }) {

            Text("Log out")

        }

    }

}