package com.bugrahankaramollaoglu.tasty.view.app_screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.CustomRatingBar
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@Composable
fun DetailsScreen(
    foodId: Int?,
    navController: NavController,
    foodViewModel: FoodViewModel = viewModel()
) {

    var isFavorite by remember { mutableStateOf(false) }
    var rating by remember { mutableIntStateOf(2) }
    val foods by foodViewModel.foods.collectAsState()

    var chosenFoodId by remember { mutableStateOf(foodId) }

//    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"

    LaunchedEffect(foodId) {
        Log.d("mesaj", "DetailsScreen received foodId = $foodId")
        chosenFoodId = foodId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        CanvasHeader()

        Spacer(Modifier.height(20.dp))


        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {


            IconButton(
                onClick = {

                    navController.popBackStack()
                },


                ) {
                Icon(
                    imageVector = Icons.Default.Close,

                    tint = Color.Black,

                    modifier = Modifier.size(30.dp),

                    contentDescription = "Go Back"
                )
            }

            Text(
                text = "Food Details", style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = myFontJomhuria,
                    color = Color.Black,
                ), modifier = Modifier.padding(top = 5.dp)
            )

            IconButton(onClick = { isFavorite = !isFavorite }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                    tint = if (isFavorite) CustomColors.CustomBlack else CustomColors.CustomBlack
                )
            }


        }

        Button(
            onClick = {
                Log.d("mesaj", foods.size.toString())
            }) {
            Text("gelen id: $chosenFoodId")
        }

//        Spacer(Modifier.height(30.dp))

        CustomRatingBar(
            rating = rating, onRatingChanged = {
                Log.d("mesaj", "changed to ${rating}")
                rating = it
            })

        AsyncImage(
            model = null,
//            contentDescription = food.name,
            contentDescription = "nameee",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

    }
}


