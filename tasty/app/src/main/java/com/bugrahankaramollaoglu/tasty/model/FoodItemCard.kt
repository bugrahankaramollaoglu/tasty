package com.bugrahankaramollaoglu.tasty.model

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteFood
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel


// her bir food kartını temsil eden UI widgeti
@ExperimentalMaterialApi
@Composable
fun FoodItemCard(
    food: Food,
    username: String,
    foodViewModel: FoodViewModel,
    favouriteViewModel: FavouriteViewModel,
    onClick: () -> Unit
) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
    val isFavourite = favouriteViewModel.isFavourite(food.id)
    var context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 20.dp,

        onClick = onClick,
        backgroundColor = CustomColors.CustomWhite
    ) {
        Column(
            modifier = Modifier.padding(
                top = 0.dp, start = 10.dp, end = 10.dp, bottom = 12.dp
            )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    "#${food.id}", style = TextStyle(
                        fontFamily = myFontJomhuria,
                        fontSize = 30.sp,
                    ), modifier = Modifier.padding(top = 10.dp)
                )

                IconButton(onClick = {
                    if (isFavourite) {

                        favouriteViewModel.removeFavourite(
                            FavouriteFood(food.id, food.name, food.imageName, food.price)
                        )
                        Log.d(
                            "mesaj",
                            "${food.name} with ${food.id} has been removed from favouritess."
                        )
                    } else {
                        favouriteViewModel.addFavourite(
                            FavouriteFood(food.id, food.name, food.imageName, food.price)
                        )
                        Log.d(
                            "mesaj", "${food.name} with ${food.id} has been added to favouritess."
                        )
                    }
                }) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavourite) "Remove from Favorites" else "Add to Favorites",
                        tint = if (isFavourite) Color.Red else CustomColors.CustomBlack
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = imageUrl,
                contentDescription = food.name,
                modifier = Modifier
                    .height(100.dp)
                    .clickable {
                        Log.d("mesaj", "${favouriteViewModel.favourites}")
                    }
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.height(15.dp))

            Row(

                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Column {
                    Text(
                        text = food.name, style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = myFontJomhuria
                        )
                    )
                    Text(text = "${food.price}₺", style = MaterialTheme.typography.button)
                }
                Button(
                    modifier = Modifier
                        .size(
                            width = 40.dp, height = 35.dp
                        )
                        .align(Alignment.CenterVertically), colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColors.CustomBlack2,
                        contentColor = CustomColors.CustomWhite
                    ), onClick = {


                        showDialog = true

                        /*foodViewModel.addFoodToBasket(
                            food,
                            1,
                            username
                        )*/

                    }) {

                    if (showDialog) {
                        AddFoodDialog(onDismissRequest = { showDialog = false }, onConfirm = {

                            // Here you can call your ViewModel's addFoodToBasket or any action
                            foodViewModel.addFoodToBasket(food, 1, username)
                            showDialog = false
                        }, onCancel = { showDialog = false }, title = {
                            Text(
                                "Add Food", style = TextStyle(
                                    fontFamily = myFontJomhuria,
                                    fontSize = 40.sp,

                                    )
                            )
                        }, text = {
                            Text(
                                "Do you want to add \"${food.name}\"\nto your basket?",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                )
                            )
                        })
                    }

                    Text(
                        text = "+", style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun AddFoodDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = title,
        text = text,
        contentColor = Color.Black,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = CustomColors.CustomYellow,
        confirmButton = {
            TextButton(
                onClick = onConfirm, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = CustomColors.CustomRed, contentColor = Color.White
                ), shape = RoundedCornerShape(12.dp), modifier = Modifier.padding(7.dp)
            ) {
                Text("OK")
            }

        },
        dismissButton = {
            TextButton(
                onClick = onCancel, colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black
                ), modifier = Modifier.padding(7.dp)
            ) {
                Text("Cancel")
            }
        })
}
