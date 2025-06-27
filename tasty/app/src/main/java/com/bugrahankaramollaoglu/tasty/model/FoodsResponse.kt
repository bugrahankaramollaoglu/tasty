package com.bugrahankaramollaoglu.tasty.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugrahankaramollaoglu.tasty.data.FoodRepository
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@JsonClass(generateAdapter = true)
data class FoodResponse(
    @Json(name = "yemekler")
    val foods: List<FoodNetworkItem>
)


@JsonClass(generateAdapter = true)
data class FoodNetworkItem(
    @Json(name = "yemek_id")
    val id: Int,
    @Json(name = "yemek_adi")
    val name: String,
    @Json(name = "yemek_resim_adi")
    val imageName: String,
    @Json(name = "yemek_fiyat")
    val price: Double
)


class FoodViewModel(
    private val repository: FoodRepository = FoodRepository()
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodNetworkItem>>(emptyList())
    val foods: StateFlow<List<FoodNetworkItem>> = _foods

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchFoods()
    }

    private fun fetchFoods() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val foodList = repository.getFoods()
                _foods.value = foodList
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isLoading.value = false
        }
    }
}