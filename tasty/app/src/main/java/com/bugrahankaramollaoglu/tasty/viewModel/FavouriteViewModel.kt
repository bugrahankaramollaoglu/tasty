package com.bugrahankaramollaoglu.tasty.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.bugrahankaramollaoglu.tasty.model.FavouriteFood

class FavouriteViewModel : ViewModel() {

    private val _favourites = mutableStateListOf<FavouriteFood>()
    val favourites: List<FavouriteFood> get() = _favourites

    fun addFavourite(food: FavouriteFood) {
        if (!_favourites.any { it.id == food.id }) {
            _favourites.add(food)
        }
    }

    fun removeFavourite(food: FavouriteFood) {
        _favourites.removeAll { it.id == food.id }
    }

    fun isFavourite(foodId: Int): Boolean {
        return _favourites.any { it.id == foodId }
    }

}