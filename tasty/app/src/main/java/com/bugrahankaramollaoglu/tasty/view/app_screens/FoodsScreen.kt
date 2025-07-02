package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.model.FoodItemCard
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodsScreen(authViewModel: AuthViewModel, navController: NavController) {

    val foodViewModel: FoodViewModel = viewModel()
    val favouriteViewModel: FavouriteViewModel = viewModel()
    val foods by foodViewModel.foods.collectAsState()
    val isLoading by foodViewModel.isLoading.collectAsState()
    val errorMessage by foodViewModel.errorMessage.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    val filteredFoods = foods.filter { it ->
        it.name.contains(searchQuery, ignoreCase = true)
    }

    val favorutes = favouriteViewModel.favourites

    val username = authViewModel.loggedInUsername

    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {

        CanvasHeader(
            modifier = Modifier.clickable {
                Log.d("mesaj", "${favorutes.size}")
            })

        Spacer(Modifier.height(10.dp))

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

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage != null) {
            Text(
                text = errorMessage ?: "Unknown error",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(filteredFoods) { food ->
                    FoodItemCard(
                        food = food,
                        authViewModel.loggedInUsername!!,
                        foodViewModel,
                        favouriteViewModel
                    ) {
                        navController.navigate("details/${food.id}")
                    }
                }
            }
        }
        Spacer(Modifier.height(50.dp))
    }
}