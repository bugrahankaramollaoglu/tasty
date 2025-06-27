package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.model.Food
import com.bugrahankaramollaoglu.tasty.model.FoodItem
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors


@Composable
fun FoodsScreen() {

    var searchQuery by remember { mutableStateOf("") }

    val foods = listOf(
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Pasta", R.drawable.login_logo, true, true, 11.99),
        Food(4, "Makarna", R.drawable.login_logo, false, true, 11.99),
        Food(4, "Lazanya", R.drawable.login_logo, false, true, 11.99),
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

        CanvasHeader()

        Column {

            Spacer(Modifier.height(110.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search foods...") },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = CustomColors.CustomWhite2,
                    focusedIndicatorColor = CustomColors.CustomBlack,
                    focusedLabelColor = CustomColors.CustomYellow
                ),
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
