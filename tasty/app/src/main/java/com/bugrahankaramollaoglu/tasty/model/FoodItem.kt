package com.bugrahankaramollaoglu.tasty.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.squareup.moshi.Json

data class FoodItemDto(
    @Json(name = "yemek_id") val id: Int,
    @Json(name = "yemek_adi") val name: String,
    @Json(name = "yemek_resim_adi") val imageName: String,
    @Json(name = "yemek_fiyat") val price: Double
)

@Composable
fun FoodItem(food: FoodNetworkItem) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = CustomColors.CustomWhite
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = imageUrl,
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
        }
    }
}

/*

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

            AsyncImage(
                model = food.imageUrl,
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
                */
/*   if (food.isFavourite) {
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
                   }*//*

            }
        }
    }
}*/
