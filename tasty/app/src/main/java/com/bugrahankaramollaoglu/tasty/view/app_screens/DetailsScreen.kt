package com.bugrahankaramollaoglu.tasty.view.app_screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.model.AddFoodDialog
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.CustomRatingBar
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.BasketViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteFood
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailsScreen(
    foodId: Int?,
    navController: NavController,
    authViewModel: AuthViewModel,
    foodViewModel: FoodViewModel,
    basketViewModel: BasketViewModel,
    favouriteViewModel: FavouriteViewModel
) {
    val foods by foodViewModel.foods.collectAsState()
    val food = foods.find { it.id == foodId }
    var isFavourite = favouriteViewModel.isFavourite(foodId ?: 0)
    val context = LocalContext.current
    var quantity by rememberSaveable { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }


    food?.let { food ->

        // pe.hu free hosting servis url adresidir
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"

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
                        tint = CustomColors.CustomYellow,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Go Back"
                    )
                }

                Text(
                    text = "Food Details", style = TextStyle(
                        fontSize = 40.sp,
                        fontFamily = myFontJomhuria,
                        color = CustomColors.CustomYellow,
                    ), modifier = Modifier.padding(top = 5.dp)
                )

                IconButton(onClick = {

                    /* data class FavouriteFood(
                        val id: Int,
                        val name: String,
                        val imageName: String,
                        val price: Int,
                    )*/

                    if (isFavourite) {
                        favouriteViewModel.removeFavourite(
                            FavouriteFood(
                                food.id, food.name, food.imageName, food.price
                            )
                        )
                    } else {

                        favouriteViewModel.addFavourite(
                            FavouriteFood(
                                food.id, food.name, food.imageName, food.price
                            )
                        )

                    }

//                    Log.d("mesaj", "foodid: ${food.id}")
//                    Log.d("mesaj", "foodid: ${food.name}")
//                    Log.d("mesaj", "foodid: ${isFavourite}")

                }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        tint = CustomColors.CustomYellow,
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavourite) "Remove from Favourites" else "Add to Favourites",
                    )
                }
            }

            Spacer(Modifier.height(30.dp))

            var rating by remember { mutableIntStateOf(2) }
            CustomRatingBar(
                rating = rating, onRatingChanged = {
                    rating = it
                })

            Box(
                modifier = Modifier.size(250.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = food.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "${food?.price ?: "N/A"} ₺",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = CustomColors.CustomYellow),
            )

            Text(
                text = "${food?.name ?: "UNKNOWN"}",
                fontSize = 65.sp,
                fontFamily = myFontJomhuria,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            Row(
            ) {
                Button(
                    modifier = Modifier
                        .size(
                            width = 50.dp, height = 50.dp
                        )

                        .align(Alignment.CenterVertically), colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColors.CustomYellow,
                        contentColor = CustomColors.CustomBlack
                    ), onClick = {
                        if (quantity > 0) {
                            quantity--
                        }
                    }, shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "-", style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    )
                }

                Spacer(Modifier.width(20.dp))

                Text(
                    text = quantity.toString(), style = TextStyle(
                        fontSize = 80.sp, fontFamily = myFontJomhuria
                    ), modifier = Modifier.padding(horizontal = 10.dp)
                )

                Spacer(Modifier.width(20.dp))

                Button(
                    modifier = Modifier
                        .size(
                            width = 50.dp, height = 50.dp
                        )
                        .align(Alignment.CenterVertically), colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColors.CustomYellow,
                        contentColor = CustomColors.CustomBlack
                    ), onClick = {
                        quantity++
                    }, shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "+", style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    )
                }
            }

            Spacer(Modifier.height(10.dp))


            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
            ) {

                Button(
                    modifier = Modifier
                        .size(
                            width = 250.dp, height = 50.dp
                        )
                        .align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColors.CustomBlack,
                        contentColor = CustomColors.CustomWhite2
                    ), onClick = {
                        if (quantity > 0) {
                            showDialog = true
                        }

                    }, shape = RoundedCornerShape(6.dp)
                ) {

                    if (showDialog) {
                        AddFoodDialog(onDismissRequest = { showDialog = false }, onConfirm = {

                            // Here you can call your ViewModel's addFoodToBasket or any action
                            foodViewModel.addFoodToBasket(
                                food, quantity, authViewModel.loggedInUsername!!
                            )
                            basketViewModel.addBasketAmount(food.price * quantity)
                            Toast.makeText(context, "${food.name} is added!", Toast.LENGTH_SHORT)
                                .show()
                            showDialog = false
                            quantity = 0
                        }, onCancel = { showDialog = false }, title = {
                            Text(
                                "Add Food", style = TextStyle(
                                    fontFamily = myFontJomhuria,
                                    fontSize = 40.sp,

                                    )
                            )
                        }, text = {
                            Text(
                                buildAnnotatedString {
                                    append("Do you want to add ")
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 20.sp,
                                            color = CustomColors.CustomRed,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    ) { // Change color to whatever you want
                                        append("$quantity ")
                                        append(food.name)
                                    }
                                    append(" to your basket?")
                                }, style = TextStyle(
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                )
                            )
                        })
                    }


                    Text(
                        text = "Add to Basket", style = TextStyle(
                            fontSize = 35.sp,
                            color = CustomColors.CustomYellow,
                            fontFamily = myFontJomhuria,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    )
                }

                Spacer(Modifier.height(20.dp))

                HorizontalDivider(
                    Modifier
                        .width(300.dp)
                        .align(Alignment.CenterHorizontally),
                    color = CustomColors.CustomYellow,

                    )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(horizontal = 50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Current Basket:", style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = myFontJomhuria,
                        )
                    )

                    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = "${basketViewModel.basketAmount.value}", style = TextStyle(
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = myFontJomhuria,
                                color = CustomColors.CustomYellow,
                                shadow = Shadow(
                                    color = CustomColors.CustomYellow.copy(alpha = 0.7f),
                                    offset = Offset(0f, 0f),
                                    blurRadius = 20f
                                )
                            )
                        )
                        Text(
                            text = " ₺",
                            style = TextStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = CustomColors.CustomYellow,
                                shadow = Shadow(
                                    color = CustomColors.CustomYellow.copy(alpha = 0.7f),
                                    offset = Offset(0f, 0f),
                                    blurRadius = 20f
                                )
                            ),
                        )
                    }
                }


            }

        }

    } ?: run {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomColors.CustomRed),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = CustomColors.CustomYellow
            )
        }
    }
}


