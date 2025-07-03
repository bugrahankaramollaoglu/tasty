package com.bugrahankaramollaoglu.tasty.model

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteFood
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouriteItemCard(
    favouriteFood: FavouriteFood, favouriteViewModel: FavouriteViewModel
) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${favouriteFood.imageName}"
    val isFavourite = favouriteViewModel.isFavourite(favouriteFood.id)


    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = CustomColors.CustomWhite2
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = favouriteFood.name,
                modifier = Modifier.size(70.dp)
            )

            Text(
                text = favouriteFood.name, style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = myFontJomhuria,
                    fontWeight = FontWeight.Bold,
                )
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    if (isFavourite) {

                        favouriteViewModel.removeFavourite(
                            FavouriteFood(
                                favouriteFood.id,
                                favouriteFood.name,
                                favouriteFood.imageName,
                                favouriteFood.price
                            )
                        )
                        Log.d(
                            "mesaj",
                            "${favouriteFood.name} with ${favouriteFood.id} has been removed from favouritess."
                        )
                    } else {
                        favouriteViewModel.addFavourite(
                            FavouriteFood(
                                favouriteFood.id,
                                favouriteFood.name,
                                favouriteFood.imageName,
                                favouriteFood.price
                            )
                        )
                        Log.d(
                            "mesaj",
                            "${favouriteFood.name} with ${favouriteFood.id} has been added to favouritess."
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
        }
    }
}

