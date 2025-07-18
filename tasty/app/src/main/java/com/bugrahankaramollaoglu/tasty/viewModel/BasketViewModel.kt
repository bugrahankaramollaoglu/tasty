package com.bugrahankaramollaoglu.tasty.viewModel

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class BasketDataStore(context: Context) {
    private val Context.dataStore by preferencesDataStore("basket_prefs")

    private val BASKET_KEY = intPreferencesKey("basket_amount")

    private val dataStore = context.dataStore

    suspend fun saveBasketAmount(amount: Int) {
        dataStore.edit { preferences ->
            preferences[BASKET_KEY] = amount
        }
    }

    fun getBasketAmount(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[BASKET_KEY] ?: 0
        }
    }
}

class BasketViewModelFactory(private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BasketViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class BasketViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = BasketDataStore(application)

    private val _basketAmount = MutableStateFlow(0)
    var basketAmount: StateFlow<Int> = _basketAmount

    init {
        // Load from DataStore when app starts
        viewModelScope.launch {
            dataStore.getBasketAmount().collect {
                _basketAmount.value = it
            }
        }
    }

    fun addBasketAmount(amount: Int) {
        val newAmount = _basketAmount.value + amount
        updateBasketAmount(newAmount)
    }

    fun extractBasketAmount(amount: Int) {
        val newAmount = _basketAmount.value - amount
        updateBasketAmount(newAmount)
    }

    fun resetAmount() {
        updateBasketAmount(0)
    }

    private fun updateBasketAmount(amount: Int) {
        _basketAmount.value = amount
        viewModelScope.launch {
            dataStore.saveBasketAmount(amount)
        }
    }
}
