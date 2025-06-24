package com.bugrahankaramollaoglu.tasty

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AuthViewModelFactory(
    private val repository: AuthRepository, private val prefManager: PreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return AuthViewModel(repository, prefManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AuthViewModel(
    private val repository: AuthRepository, private val prefManager: PreferencesManager
) : ViewModel() {

    // Login states
    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    var loggedInUsername by mutableStateOf<String?>(null)
        private set

    // Register states
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    init {
        if (prefManager.isLoggedIn()) {
            loggedInUsername = prefManager.getUsername()
            loginState = LoginState.Success("Welcome back, $loggedInUsername")
        }
    }

    fun isLoggedIn(): Boolean = prefManager.isLoggedIn()

    // Login function
    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginState = LoginState.Loading
            val result = repository.login(username, password)
            loginState = if (result.isSuccess) {
                prefManager.setLoggedIn(true)
                prefManager.setUsername(username)
                loggedInUsername = username
                LoginState.Success(result.getOrNull()?.message ?: "Logged in")
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Login failed")
            }
        }
    }

    // Register function
    fun register(username: String, email: String, password: String, password2: String) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.registerUser(
                    RegisterRequest(username, email, password, password2)
                )
                if (response.isSuccessful) {
                    prefManager.setLoggedIn(true)
                    prefManager.setUsername(username ?: email)
                    _registerState.value = RegisterState.Success("Registration successful")
                } else {
                    val errorBody = response.errorBody()?.string()
                    _registerState.value = RegisterState.Error(errorBody ?: "Unknown error")
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Network error: ${e.localizedMessage}")
            }
        }
    }

    fun resetLoginState() {
        loginState = LoginState.Idle
    }

    fun resetRegisterState() {
        _registerState.value = RegisterState.Idle
    }

    fun logout() {
        prefManager.setLoggedIn(false)
        loggedInUsername = null
        loginState = LoginState.Idle

    }
}


sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val message: String) : RegisterState()
    data class Error(val error: String) : RegisterState()
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val message: String) : LoginState()
    data class Error(val error: String) : LoginState()
}