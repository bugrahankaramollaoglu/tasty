package com.bugrahankaramollaoglu.tasty.view.app_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.model.BasketItemCard
import com.bugrahankaramollaoglu.tasty.model.Food
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@Composable
fun BasketScreen(
    navController: NavController, authViewModel: AuthViewModel, foodViewModel: FoodViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CanvasHeader()

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {


            Text(
                text = "My Basket",
                Modifier.clickable(onClick = {
                    foodViewModel.getBasket(authViewModel.loggedInUsername!!)
                }),
                style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = myFontJomhuria,
                    color = CustomColors.CustomWhite2,
                ),
            )

        }

        val food1 = Food(1, "ayran", "ayran.png", 40)
        val food2 = Food(2, "su", "su.png", 10)
        val food3 = Food(3, "pizza", "pizza.png", 150)

//        val foodList: List<Food> = listOf(food1, food2, food3)

        val foodList = mutableListOf<Food>()
        foodList.add(food1)
        foodList.add(food2)
        foodList.add(food3)


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(foodList) { food ->
                BasketItemCard(food = food, authViewModel, foodViewModel)
            }
        }


    }


}




