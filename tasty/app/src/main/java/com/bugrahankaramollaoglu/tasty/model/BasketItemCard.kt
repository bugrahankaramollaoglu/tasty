package com.bugrahankaramollaoglu.tasty.model

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.api.FoodInBasket
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketItemCard(
    foodInBasket: FoodInBasket,
    authViewModel: AuthViewModel,
    foodViewModel: FoodViewModel
) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${foodInBasket.imageName}"
    val price = foodInBasket.price.toIntOrNull() ?: 0
    val quantity = foodInBasket.quantity.toIntOrNull() ?: 0

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
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = foodInBasket.name,
                modifier = Modifier.size(70.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = foodInBasket.name,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = myFontJomhuria,
                        fontWeight = FontWeight.Bold,
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Price: $price ₺",
                    style = TextStyle(fontSize = 14.sp)
                )

                Text(
                    text = "Quantity: $quantity",
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {

                    Log.d("mesaj", "id: ${foodInBasket.basketFoodId}")

                    // Delete item from basket
                    foodViewModel.deleteBasketItem(
                        foodInBasket.basketFoodId.toInt(),
                        authViewModel.loggedInUsername!!
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Total: ${price * quantity} ₺",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

