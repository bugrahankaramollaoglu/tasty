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
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@Composable
fun BasketScreen(
    navController: NavController, authViewModel: AuthViewModel, foodViewModel: FoodViewModel
) {

    val basketItems = foodViewModel.basketItems.collectAsState(emptyList()).value
//    val basketItems by foodViewModel.basketItems.collectAsState(initial = emptyList())

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

        /*        val food1 = Food(1, "ayran", "ayran.png", 40)
                val food2 = Food(2, "su", "su.png", 10)
                val food3 = Food(3, "pizza", "pizza.png", 150)

        //        val foodList: List<Food> = listOf(food1, food2, food3)

                val foodList = mutableListOf<Food>()
                foodList.add(food1)
                foodList.add(food2)
                foodList.add(food3)*/


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
        ) {
            if (basketItems.isNullOrEmpty()) {

                Image(
                    painter = painterResource(id = R.drawable.empty_basket2),
                    contentDescription = "Basket is empty",
                    modifier = Modifier
                        .size(450.dp)  // sets width and height to 200dp
                        .padding(16.dp)
                )

            } else {
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
                            foodViewModel = foodViewModel
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
                "410 ₺", style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = CustomColors.CustomYellow,
                )
            )
        }

        Spacer(Modifier.height(7.dp))

        Button(
            onClick = { /* tıklama işlemi */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CustomColors.CustomYellow, contentColor = Color.White
            )
        ) {
            Text(
                text = "CHECKOUT", style = TextStyle(
                    fontSize = 40.sp, fontFamily = myFontJomhuria,
//                    fontWeight = FontWeight.Bold,
                    color = CustomColors.CustomBlack,
                )
            )
        }

    }
}

