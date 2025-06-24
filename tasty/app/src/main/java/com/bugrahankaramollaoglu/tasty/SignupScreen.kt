package com.bugrahankaramollaoglu.tasty

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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.bugrahankaramollaoglu.tasty.util.CustomButton
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.CustomTextField
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria


@Composable
fun SignUpScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {

    val registerState by viewModel.registerState.collectAsState()

    /* ---------- UI state ---------- */
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    /* ---------- Observe sign‑up outcome ---------- *//*LaunchedEffect(signUpResult) {
        signUpResult?.let { result ->
            if (result.isSuccess) {
                message = "Sign‑up successful!"
                onSignUpSuccess()
            } else {
                message = "Error: ${result.exceptionOrNull()?.message ?: "Unknown error"}"
            }
        }
    }



*//* ---------- Bottom‑sheet scaffold ---------- */
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetPeekHeight = 450.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .background(CustomColors.CustomYellow),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                /* top grabber */
                Box(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .width(40.dp)
                        .height(4.dp)
                        .background(Color.Gray, RoundedCornerShape(2.dp))
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
                        text = "Sign Up",
                        fontSize = 50.sp,
                        fontFamily = myFontJomhuria,
                        color = CustomColors.CustomRed,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {


                    /* email */
                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.padding(
                            horizontal = 30.dp
                        ),
                        placeholder = "Enter email",
                        backgroundColor = CustomColors.CustomWhite2,
                        contentColor = Color.DarkGray,
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) })

                    Spacer(Modifier.height(16.dp))

                    /* password */
                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Enter password",
                        backgroundColor = CustomColors.CustomWhite2,
                        isPassword = true,
                        modifier = Modifier.padding(
                            horizontal = 30.dp
                        ),
                        contentColor = Color.DarkGray,
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) })

                    Spacer(Modifier.height(16.dp))

                    /* confirm password */
                    CustomTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        isPassword = true,
                        placeholder = "Confirm password",
                        backgroundColor = CustomColors.CustomWhite2,
                        contentColor = Color.DarkGray,
                        modifier = Modifier.padding(
                            horizontal = 30.dp
                        ),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) })

                    Spacer(Modifier.height(30.dp))

                    /* sign‑up button */
                    CustomButton(
                        text = "Sign Up",
                        onClick = {
                            message = ""
                            when {
                                email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> message =
                                    "Please fill all the fields"

                                password != confirmPassword -> message = "Passwords do not match"
                                else -> {
                                    viewModel.register(email, email, password, password)
                                } // değiştir as signup()
                            }
                        },
                        backgroundColor = CustomColors.CustomRed,
                        contentColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    )

                    Spacer(Modifier.height(15.dp))

                    when (registerState) {
                        is RegisterState.Loading -> CircularProgressIndicator(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(28.dp),
                            color = CustomColors.CustomRed,
                            strokeWidth = 4.dp
                        )

                        is RegisterState.Success -> {
                            Text(
                                text = (registerState as RegisterState.Success).message,
                                color = MaterialTheme.colors.primary
                            )
                            LaunchedEffect(Unit) {
                                onRegisterSuccess()
                                viewModel.resetRegisterState()
                            }
                        }

                        is RegisterState.Error -> Text(
                            text = (registerState as RegisterState.Error).error,
                            color = MaterialTheme.colors.error
                        )

                        else -> {}
                    }

                    /* back to sign‑in link */
                    Text(
                        text = "Already have an account? Sign in.",
                        fontSize = 30.sp,
                        fontFamily = myFontJomhuria,
                        color = CustomColors.CustomBlack,
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .clickable {
                                navController.navigate("sign_in")
                            }, // or navController.navigate("sign_in")
                        textAlign = TextAlign.Center
                    )
                }
            }
        }) {

        /* ---------- Main screen background ---------- */
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0.10f)
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

                Spacer(Modifier.height(10.dp))

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
