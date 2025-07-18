package com.bugrahankaramollaoglu.tasty.view.app_screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel

@Composable
fun SettingsScreen(authViewModel: AuthViewModel, navController: NavController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CanvasHeader()

        Spacer(Modifier.height(40.dp))

        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(CustomColors.CustomYellow)
        )

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Hi,\t${authViewModel.loggedInUsername}.", style = TextStyle(
                fontFamily = myFontJomhuria,
                fontSize = 50.sp,
                color = CustomColors.CustomYellow,
                textAlign = TextAlign.Center
            )
        )

        Spacer(Modifier.height(40.dp))

        Button(

            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CustomColors.CustomYellow,
                contentColor = CustomColors.CustomBlack,
            ),

            onClick = {
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

            Text(
                "Log out", style = TextStyle(
                    fontFamily = myFontJomhuria,
                    fontSize = 30.sp
                )
            )

        }

    }

}