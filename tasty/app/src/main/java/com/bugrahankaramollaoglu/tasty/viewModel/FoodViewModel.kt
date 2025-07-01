package com.bugrahankaramollaoglu.tasty.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugrahankaramollaoglu.tasty.data.FoodRepository
import com.bugrahankaramollaoglu.tasty.model.Food
import com.bugrahankaramollaoglu.tasty.retrofit.FoodsInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val foodRepository: FoodRepository = FoodRepository(),
) : ViewModel() {

    private val _foods = MutableStateFlow<List<Food>>(emptyList())
    val foods: StateFlow<List<Food>> = _foods

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
                val foodList = foodRepository.getFoods()
                _foods.value = foodList
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isLoading.value = false
        }
    }


    fun addFoodToBasket(
        food: Food,
        quantity: Int,
        username: String
    ) {

        viewModelScope.launch {
            try {
                val response = FoodsInstance.basketApi.addFoodToBasket(
                    food.name,
                    food.imageName,
                    food.price,
                    quantity,
                    username
                )
                if (response.isSuccessful) {
                    val basketResponse = response.body()
                    if (basketResponse != null) {
                        if (basketResponse.success == 1) {
                            Log.d("mesaj", "Added ${food.name} to basket")
                        } else {
                            Log.d("mesaj", basketResponse.message)
                        }
                    }
                } else {
                    Log.d("mesaj", "HATA: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.d("mesaj", "Exception: ${e.localizedMessage}")
            }
        }
    }

}