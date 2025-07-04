package com.bugrahankaramollaoglu.tasty.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bugrahankaramollaoglu.tasty.data.dataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


data class FavouriteFood(
    val id: Int,
    val name: String,
    val imageName: String,
    val price: Int,
)

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    // Make this public so Composables can observe changes
//    val favourites = mutableStateListOf<FavouriteFood>()

    private val _favourites = mutableStateListOf<FavouriteFood>()
    val favourites: MutableList<FavouriteFood> get() = _favourites


    private val gson = Gson()
    private val dataStore = application.dataStore
    private val FAVOURITES_KEY = stringPreferencesKey("favourites_list")

    init {
        // Load persisted favourites on init
        viewModelScope.launch {
            loadFavourites()
        }
    }

    private suspend fun loadFavourites() {
        val prefs = dataStore.data.first()
        val json = prefs[FAVOURITES_KEY]
        if (json != null) {
            val type = object : TypeToken<List<FavouriteFood>>() {}.type
            val list: List<FavouriteFood> = gson.fromJson(json, type)
            _favourites.addAll(list)
        }
    }

    private fun saveFavourites() {
        viewModelScope.launch {
            val json = gson.toJson(_favourites.toList())
            dataStore.edit { prefs ->
                prefs[FAVOURITES_KEY] = json
            }
        }
    }

    fun addFavourite(food: FavouriteFood) {
        if (!_favourites.any { it.id == food.id }) {
            _favourites.add(food)
            saveFavourites()
        }
    }

    fun removeFavourite(food: FavouriteFood) {
        val removed = _favourites.removeAll { it.id == food.id }
        if (removed) saveFavourites()
    }

    fun updateFavourite(updatedFood: FavouriteFood) {
        val index = _favourites.indexOfFirst { it.id == updatedFood.id }
        if (index != -1) {
            _favourites[index] = updatedFood
            saveFavourites()
        }
    }

    fun isFavourite(foodId: Int): Boolean {
        return _favourites.any { it.id == foodId }
    }

}