package com.bugrahankaramollaoglu.tasty.model

data class Food(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val isFavourite: Boolean,
    val isFreeDelivery: Boolean,
    val price: Double
)