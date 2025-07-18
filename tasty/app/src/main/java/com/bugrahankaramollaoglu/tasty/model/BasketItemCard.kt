package com.bugrahankaramollaoglu.tasty.model

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bugrahankaramollaoglu.tasty.api.FoodInBasket
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.BasketViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketItemCard(
    foodInBasket: FoodInBasket,
    authViewModel: AuthViewModel,
    foodViewModel: FoodViewModel,
    basketViewModel: BasketViewModel,
) {
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${foodInBasket.imageName}"
    val price = foodInBasket.price ?: 0
    val quantity = foodInBasket.quantity ?: 0
    val totalPrice = price * quantity

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 12.dp,
        backgroundColor = Color.White
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                CustomColors.CustomYellow,
                                CustomColors.CustomYellow,
                            )
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Card(
                        modifier = Modifier.size(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = 8.dp,
                        backgroundColor = Color.White
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = foodInBasket.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 8.dp, y = (-8).dp)
                            .size(24.dp)
                            .background(
                                color = Color(0xFFFF6B6B),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$quantity",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }

                // Food Details
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = foodInBasket.name,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2D3748)
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFFF7FAFC),
                            modifier = Modifier.padding(0.dp)
                        ) {
                            Text(
                                text = "${price}₺ each",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF718096)
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = Color(0xFF718096),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "$quantity",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF718096)
                                )
                            )
                        }
                    }
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Total",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF718096)
                            )
                        )
                        Text(
                            text = "${totalPrice}₺",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2D3748)
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = Color(0xFFFED7D7),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                foodViewModel.deleteBasketItem(
                                    foodInBasket.basketFoodId.toInt(),
                                    authViewModel.loggedInUsername!!
                                )
                                basketViewModel.extractBasketAmount(totalPrice)
                                foodViewModel.getBasket(authViewModel.loggedInUsername!!)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Remove item",
                            tint = Color(0xFFE53E3E),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}