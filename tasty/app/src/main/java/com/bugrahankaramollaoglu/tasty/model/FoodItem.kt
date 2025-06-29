package com.bugrahankaramollaoglu.tasty.model

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria

/*
data class FoodItemDto(
    @Json(name = "yemek_id") val id: Int,
    @Json(name = "yemek_adi") val name: String,
    @Json(name = "yemek_resim_adi") val imageName: String,
    @Json(name = "yemek_fiyat") val price: Double
)
*/

@Composable
fun FoodItem(food: FoodNetworkItem) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 20.dp,
        backgroundColor = CustomColors.CustomWhite
    ) {
        Column(
            modifier = Modifier.padding(
                top = 0.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 12.dp
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
//                var isFavorite by remember { mutableStateOf(false) }

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
            Spacer(modifier = Modifier.height(6.dp))
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
                            width = 50.dp, height = 35.dp
                        )
                        .align(Alignment.CenterVertically), colors = ButtonDefaults.buttonColors(
                        backgroundColor = CustomColors.CustomYellow,
                        contentColor = CustomColors.CustomBlack
                    ), onClick = {}) {
                    Text(
                        text = "+", style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    )
                }

            }
        }
    }
}
