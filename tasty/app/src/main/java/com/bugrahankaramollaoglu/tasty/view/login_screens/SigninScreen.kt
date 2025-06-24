package com.bugrahankaramollaoglu.tasty.view.login_screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CustomButton
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.CustomTextField
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.LoginState


@Composable
fun SignInScreen(
    viewModel: AuthViewModel, navController: NavController, onLoginSuccess: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
//    val loginState = viewModel.loginState

    // Reset the login state whenever the screen is entered
    LaunchedEffect(Unit) {
        viewModel.resetLoginState()
    }

    val loginState = viewModel.loginState

    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            onLoginSuccess()
        }
    }

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



                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) // optional padding
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.go_back), // replace with your actual back icon
                        contentDescription = "Go Back",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)/*.background(
                                color = CustomColors.CustomWhite2,
                                shape = RoundedCornerShape(50)
                            )*/.padding(6.dp)
                            .align(Alignment.CenterStart)
                            .clickable {
                                navController.navigate("login")
                            })

                    Text(
                        text = "Login!",
                        fontSize = 50.sp,
                        fontFamily = myFontJomhuria,
                        color = CustomColors.CustomRed,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }


                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Enter email",
                    backgroundColor = CustomColors.CustomWhite2,
                    contentColor = Color.DarkGray,
                    modifier = Modifier.padding(
                        horizontal = 30.dp
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    })

                Spacer(Modifier.height(16.dp))

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter password",
                    backgroundColor = CustomColors.CustomWhite2,
                    contentColor = Color.DarkGray,
                    isPassword = true,
                    modifier = Modifier.padding(
                        horizontal = 30.dp
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null)
                    })

                Spacer(Modifier.height(25.dp))


                CustomButton(
                    text = "Sign in", onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty()) {

                            viewModel.login(email, password)

                        } else {


                        }
                    }, backgroundColor = CustomColors.CustomRed, contentColor = Color.White,

                    modifier = Modifier.padding(horizontal = 30.dp)
                )

                when (loginState) {
                    is LoginState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(48.dp),
                            color = CustomColors.CustomRed,
                            strokeWidth = 4.dp
                        )
                    }

                    is LoginState.Error -> {
                        Text(
                            text = loginState.error,
                            color = Color.Red,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    else -> {}
                }

                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Do not have an account? Sign up.",
                    fontSize = 30.sp,
                    fontFamily = myFontJomhuria,
                    color = CustomColors.CustomBlack, // or any color you want
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .clickable {
                            navController.navigate("sign_up")
                        },
                    textAlign = TextAlign.Center
                )

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
                    text = "TASTY APP",
                    fontSize = 75.sp,
                    textAlign = TextAlign.Center,
                    color = CustomColors.CustomRed,
                    fontFamily = myFontJomhuria,
                    modifier = Modifier.padding(top = 60.dp)
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

