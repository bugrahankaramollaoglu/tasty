package com.bugrahankaramollaoglu.tasty.model

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel


// her bir food kartını temsil eden UI widgeti
@ExperimentalMaterialApi
@Composable
fun FoodItemCard(
    food: Food,
    username: String,
    viewModel: FoodViewModel,
    onClick: () -> Unit
) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
    var isFavorite by remember { mutableStateOf(false) }
    var context = LocalContext.current

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

                IconButton(onClick = { isFavorite = !isFavorite }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                        tint = if (isFavorite) Color.Red else CustomColors.CustomBlack
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = imageUrl,
                contentDescription = food.name,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

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


                        Toast.makeText(
                            context,
                            "You clicked on item: ${food.id} of ${food.name}",
                            Toast.LENGTH_SHORT
                        ).show()

                        viewModel.addFoodToBasket(
                            food,
                            1,
                            username
                        )

                    }) {
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
