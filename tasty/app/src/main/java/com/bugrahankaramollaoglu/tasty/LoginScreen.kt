package com.bugrahankaramollaoglu.tasty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.util.CustomButton
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.OrDivider
import com.bugrahankaramollaoglu.tasty.util.SocialMediaButton
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria


@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)  // Set desired height
                    .background(CustomColors.CustomYellow),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 30.dp)
                        .width(40.dp)
                        .height(4.dp)
                        .background(color = Color.Gray, shape = RoundedCornerShape(2.dp))
                )

                Text(
                    text = "Welcome!",
                    fontSize = 50.sp,
                    fontFamily = myFontJomhuria,
                    color = CustomColors.CustomRed,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    text = "Sign in to user account",
                    onClick = {
                        navController.navigate("sign_in")
                    },
                    backgroundColor = CustomColors.CustomRed,
                    contentColor = Color.White,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )

                Spacer(Modifier.height(15.dp))

                CustomButton(
                    text = "Create an account",
                    onClick = {
                        navController.navigate("sign_up")
                    },
                    backgroundColor = CustomColors.CustomWhite,
                    contentColor = Color.Black,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )

                Spacer(Modifier.height(15.dp))

                OrDivider()

                Spacer(Modifier.height(15.dp))

                Row {
                    SocialMediaButton(

                        onClick = { /* handle Google sign-in */ },

                        backgroundColor = CustomColors.CustomWhite,

                        contentColor = Color.Black,

                        icon = {

                            Icon(

                                painter = painterResource(id = R.drawable.google_icon),

                                contentDescription = "Google Sign-In",

                                tint = Color.Unspecified,

                                modifier = Modifier.size(24.dp)

                            )

                        })

                    Spacer(Modifier.width(30.dp))

                    SocialMediaButton(

                        onClick = { /* handle Apple sign-in */ },

                        backgroundColor = CustomColors.CustomWhite,

                        contentColor = Color.Black,

                        icon = {

                            Icon(

                                painter = painterResource(id = R.drawable.apple_icon),

                                contentDescription = "Apple Sign-In",

                                tint = Color.Unspecified,

                                modifier = Modifier.size(24.dp)

                            )

                        })

                    Spacer(Modifier.width(30.dp))

                    SocialMediaButton(

                        onClick = { /* handle Facebook sign-in */ },

                        backgroundColor = CustomColors.CustomWhite,

                        contentColor = Color.Black,

                        icon = {

                            Icon(

                                painter = painterResource(id = R.drawable.facebook_icon),

                                contentDescription = "Facebook Sign-In",

                                tint = Color.Unspecified,

                                modifier = Modifier.size(24.dp)

                            )

                        })

                    Spacer(modifier = Modifier.height(46.dp))

                }
            }
        },
        sheetPeekHeight = 400.dp // Decide how much is shown initially
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0.1f)
            )
    
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "TASTY\nNOW",
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    color = CustomColors.CustomRed,
                    fontFamily = myFontJomhuria,
                    modifier = Modifier.padding(top = 30.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.login_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp)
                )
            }
        }
    }
}

/*

@Composable
fun LoginScreen(authViewModel: AuthViewModel, navController: NavHostController) {
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
            is LoginState.Success -> {
                // Navigate to home screen once login is successful
                LaunchedEffect(Unit) {
                    navController.navigate("home") {
                        // Clear login screen from backstack so user cannot go back with back button
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            else -> {}
        }
    }
}
*/

