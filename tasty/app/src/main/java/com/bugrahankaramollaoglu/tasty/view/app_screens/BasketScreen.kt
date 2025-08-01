package com.bugrahankaramollaoglu.tasty.view.app_screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.model.BasketItemCard
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.util.myFontWhoa
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.BasketViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@Composable
fun BasketScreen(
    navController: NavController,
    basketViewModel: BasketViewModel,
    authViewModel: AuthViewModel,
    foodViewModel: FoodViewModel
) {

    val basketItems = foodViewModel.basketItems.collectAsState(initial = null).value
    val basketAmount by basketViewModel.basketAmount.collectAsState()

    // Add a loading state
    val isLoading = basketItems == null

    LaunchedEffect(Unit) {
        foodViewModel.getBasket(authViewModel.loggedInUsername!!)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CanvasHeader()

        Spacer(Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
        ) {
            when {
                // Show loading state instead of empty state initially
                isLoading -> {
                    // You can show a loading indicator here
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Optional: Add a CircularProgressIndicator or just leave empty
                        // CircularProgressIndicator(color = CustomColors.CustomWhite)
                    }
                }

                // Show content when data is loaded and not empty
                basketItems?.isNotEmpty() == true -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "Basket", style = TextStyle(
                                fontFamily = myFontJomhuria,
                                fontSize = 50.sp,
                                color = CustomColors.CustomWhite2
                            )
                        )

                        Spacer(Modifier.height(10.dp))

                        LazyColumn(
                            modifier = Modifier
                                .height(550.dp)
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(basketItems) { item ->
                                BasketItemCard(
                                    foodInBasket = item,
                                    authViewModel = authViewModel,
                                    foodViewModel = foodViewModel,
                                    basketViewModel = basketViewModel
                                )
                            }
                        }
                    }
                }

                // Show empty state only when data is loaded and empty
                else -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.empty_basket2),
                            contentDescription = "Basket is empty",
                            modifier = Modifier
                                .size(400.dp)
                                .padding(horizontal = 16.dp)
                        )

                        Text(
                            "EMPTY", style = TextStyle(
                                fontFamily = myFontWhoa,
                                color = CustomColors.CustomYellow,
                                fontSize = 60.sp
                            )
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(30.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                "Total: ", style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = myFontJomhuria,
                    color = CustomColors.CustomWhite,
                )
            )
            Text(
                "$basketAmount ₺", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = CustomColors.CustomWhite,
                )
            )
        }

        Spacer(Modifier.height(7.dp))

        Button(
            onClick = {

                navController.navigate("checkout")

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CustomColors.CustomYellow,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "CHECKOUT", style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = myFontJomhuria,
                    color = CustomColors.CustomBlack,
                )
            )
        }
    }
}