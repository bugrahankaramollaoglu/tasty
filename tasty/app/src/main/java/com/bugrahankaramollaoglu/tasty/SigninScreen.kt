package com.bugrahankaramollaoglu.tasty


/*

@Composable
fun SignInScreen(
    viewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val loginResult = viewModel.signInResult.value

//    viewModel.signIn(email, password)

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(loginResult) {
        if (loginResult?.isSuccess == true) {
            onLoginSuccess()
        } else if (loginResult?.isFailure == true) {
            message = loginResult.exceptionOrNull()?.message ?: "Login failed"
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
                        .padding(vertical = 15.dp)
                        .width(40.dp)
                        .height(4.dp)
                        .background(color = Color.Gray, shape = RoundedCornerShape(2.dp))
                )


                Spacer(Modifier.height(15.dp))

                Column(

                    modifier = Modifier

                        .fillMaxSize()
                        .padding(horizontal = 16.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,

                    verticalArrangement = Arrangement.Top

                ) {

                    Text(
                        text = "Sign In",
                        fontSize = 50.sp,
                        fontFamily = myFontJomhuria,
                        color = CustomColors.CustomRed, // or any color you want
                        modifier = Modifier.padding(bottom = 32.dp),
                        textAlign = TextAlign.Center
                    )


                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Enter email",
                        backgroundColor = CustomColors.CustomWhite2,
                        contentColor = Color.DarkGray,
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        })

                    Spacer(modifier = Modifier.height(16.dp))

                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Enter password",
                        backgroundColor = CustomColors.CustomWhite2,
                        contentColor = Color.DarkGray,
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        })


                    Spacer(Modifier.height(25.dp))

                    CustomButton(
                        text = "Sign in",
                        onClick = {
                            if (email.isNotEmpty() && password.isNotEmpty()) {

                                viewModel.login(email, password)

                            } else {

                                message = "Please enter both email and password"

                            }
                        },
                        backgroundColor = CustomColors.CustomRed,
                        contentColor = Color.White,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )

                    Spacer(Modifier.height(15.dp))

                    Text(
                        text = "Do not have an account? Sign up.",
                        fontSize = 30.sp,
                        fontFamily = myFontJomhuria,
                        color = CustomColors.CustomBlack, // or any color you want
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .clickable {
                                navController.navigate("sign_up")
                            },
                        textAlign = TextAlign.Center
                    )

                }


            }
        },
        sheetPeekHeight = 400.dp // Decide how much is shown initially
    ) {
        // Main content
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

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.login_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(250.dp)
                )
            }
        }
    }
}
*/


