package com.bugrahankaramollaoglu.tasty.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


data class FavouriteFood(
    val id: Int,
    val name: String,
    val imageName: String,
    val price: Int,
)


class FavouriteViewModel : ViewModel() {

    // Make this public so Composables can observe changes
    val favourites = mutableStateListOf<FavouriteFood>()


    fun addFavourite(food: FavouriteFood) {
        if (!favourites.any { it.id == food.id }) {
            favourites.add(food)
        }
    }

    fun removeFavourite(food: FavouriteFood) {
        favourites.removeAll { it.id == food.id }
    }

    fun updateFavourite(updatedFood: FavouriteFood) {
        val index = favourites.indexOfFirst { it.id == updatedFood.id }
        if (index != -1) {
            favourites[index] = updatedFood
        }
    }

    fun isFavourite(foodId: Int): Boolean {
        return favourites.any { it.id == foodId }
    }

}