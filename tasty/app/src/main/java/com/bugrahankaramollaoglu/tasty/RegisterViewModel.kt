package com.bugrahankaramollaoglu.tasty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val message: String) : RegisterState()
    data class Error(val error: String) : RegisterState()
}

class RegisterViewModel : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun registerUser(username: String, email: String, password: String, password2: String) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.registerUser(
                    RegisterRequest(username, email, password, password2)
                )
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success("Registration successful")
                } else {
                    // Handle error from backend
                    val errorBody = response.errorBody()?.string()
                    _registerState.value = RegisterState.Error(errorBody ?: "Unknown error")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Network error: ${e.localizedMessage}")
            }
        }
    }

    fun resetState() {
        _registerState.value = RegisterState.Idle
    }
}
