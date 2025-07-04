package com.bugrahankaramollaoglu.tasty.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugrahankaramollaoglu.tasty.api.BasketResponse
import com.bugrahankaramollaoglu.tasty.api.FoodInBasket
import com.bugrahankaramollaoglu.tasty.data.FoodRepository
import com.bugrahankaramollaoglu.tasty.model.Food
import com.bugrahankaramollaoglu.tasty.retrofit.FoodsInstance
import com.squareup.moshi.Moshi
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


    /* *********** sepetteki ürünler ************ */

    private val _basketItems = MutableStateFlow<List<FoodInBasket>>(emptyList())
    val basketItems: StateFlow<List<FoodInBasket>> = _basketItems

    /* *********************** */

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

    // we need manual parsing BECAUSE
    // getFoodsAtBasket api does not return a valid empty json body
    // if basket is empty, it returns totally empty. so, moshi crashes
    // trying to parse an empty body. so we are manually parsing it
    inline fun <reified T> safeParse(json: String): T? {
        return try {
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(T::class.java)
            adapter.fromJson(json)
        } catch (e: Exception) {
            null
        }
    }

    fun addFoodToBasket(
        food: Food, quantity: Int, username: String
    ) {
        viewModelScope.launch {

            try {

                // ✅ Force update local basket cache before proceeding
                getBasket(username)

                // 🔁 Add a small delay to give time for _basketItems to update (only if needed)
//                kotlinx.coroutines.delay(300)

                // Step 1: Check if food already exists in the basket
                val existingItem = _basketItems.value.find { it.name == food.name }

                if (existingItem != null) {
                    // Step 2: Combine quantities
                    val updatedQuantity = (existingItem.quantity ?: 0) + quantity

                    // Step 3: Delete the old item
                    FoodsInstance.basketApi.deleteFromBasket(
                        existingItem.basketFoodId.toInt(),
                        username
                    )

//                    existingItem.quantity = 0

                    // Step 4: Add again with updated quantity
                    val response = FoodsInstance.basketApi.addFoodToBasket(
                        food.name,
                        food.imageName,
                        food.price,
                        updatedQuantity,
                        username
                    )

                    if (response.isSuccessful) {
                        Log.d("mesaj", "Updated quantity of ${food.name} to $updatedQuantity")
                        getBasket(username) // refresh
                    } else {
                        Log.d("mesaj", "Failed to update ${food.name}")
                    }

                } else {
                    // If it doesn't exist, add normally
                    val response = FoodsInstance.basketApi.addFoodToBasket(
                        food.name,
                        food.imageName,
                        food.price,
                        quantity,
                        username
                    )
                    if (response.isSuccessful) {
                        Log.d("mesaj", "Added ${food.name} to basket")
                        getBasket(username) // refresh
                    } else {
                        Log.d("mesaj", "Failed to add ${food.name}")
                    }
                }

            } catch (e: Exception) {
                Log.d("mesaj", "Exception: ${e.localizedMessage}")
            }
        }
    }


    fun deleteBasketItem(basketFoodId: Int, username: String) {

        viewModelScope.launch {

            try {
                val response = FoodsInstance.basketApi.deleteFromBasket(basketFoodId, username)
                if (response.isSuccessful) {
                    Log.d("mesaj", "DELETED")
                    _basketItems.value =
                        _basketItems.value.filterNot { it.basketFoodId == basketFoodId.toString() }
                    getBasket(username)
                } else {

                }
            } catch (e: Exception) {

            }

        }

    }


    fun getBasket(username: String) {
        viewModelScope.launch {
            try {
                val response = FoodsInstance.basketApi.getBasketItems(username)
                if (response.isSuccessful) {
                    val body = response.body()?.string()

                    if (body.isNullOrBlank()) {
                        Log.d("mesaj", "BASKET IS EMPTY (no content)")
                        _basketItems.value = emptyList()
                        return@launch
                    }

                    // 👇 Use your generic safeParse function
                    val basketResponse = safeParse<BasketResponse>(body)

                    if (basketResponse?.basketItems.isNullOrEmpty()) {
                        Log.d("mesaj", "BASKET IS EMPTY (parsed empty list)")
                        _basketItems.value = emptyList()
                    } else {
                        _basketItems.value = basketResponse.basketItems
                        Log.d("mesaj", "sepeting: ${basketResponse?.basketItems}")
                    }

                } else {
                    Log.d("mesaj", "Server Error: ${response.code()}")
                    _basketItems.value = emptyList()
                }
            } catch (e: Exception) {
                Log.d("mesaj", "Exception: ${e.message}")
                _basketItems.value = emptyList()
            }
        }
    }

}