package com.bugrahankaramollaoglu.tasty.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bugrahankaramollaoglu.tasty.data.FoodRepository
import com.bugrahankaramollaoglu.tasty.model.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val repository: FoodRepository = FoodRepository()
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