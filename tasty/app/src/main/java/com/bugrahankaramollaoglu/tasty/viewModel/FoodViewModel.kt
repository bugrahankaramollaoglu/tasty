package com.bugrahankaramollaoglu.tasty.viewModel

/*
class FoodViewModel : ViewModel() {
    var foods by mutableStateOf<List<FoodItemDto>>(emptyList())
        private set

    init {
        getFoods()
    }

    fun getFoods() {
        viewModelScope.launch {
            try {
                val response = FoodsInstance.api.getAllFoods()
                foods = response.foods
            } catch (e: Exception) {
                Log.d("mesaj", "Error: ${e.message}")
            }
        }
    }
}
*/
