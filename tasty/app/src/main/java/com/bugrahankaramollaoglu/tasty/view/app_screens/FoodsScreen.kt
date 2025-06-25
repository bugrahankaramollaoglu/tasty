package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.model.Food
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria


@Composable
fun FoodItem(food: Food) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = CustomColors.CustomWhite
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = food.name,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = food.name, style = MaterialTheme.typography.subtitle2)

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "$${food.price}", style = MaterialTheme.typography.body2)

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (food.isFavourite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favourite",
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                if (food.isFreeDelivery) {
                    Text(
                        text = "Free Delivery",

                        style = TextStyle(fontFamily = myFontJomhuria, fontSize = 35.sp),
                        color = Color.Black,
                    )
                }
            }
        }
    }
}


@Composable
fun FoodsScreen() {

    var searchQuery by remember { mutableStateOf("") }


    val foods = listOf(
        Food(1, "Pizza", R.drawable.login_logo, true, true, 12.99),
        Food(2, "Burger", R.drawable.login_logo, true, false, 8.99),
        Food(3, "Sushi", R.drawable.login_logo, true, false, 15.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, false, true, 11.99),
        // add more food objects
    )


    val filteredFoods = foods.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {

        Column {

            Spacer(Modifier.height(100.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search foods...") },
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") })

            Spacer(Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredFoods) { food ->
                    FoodItem(food = food)
                }
            }
        }
    }
}
