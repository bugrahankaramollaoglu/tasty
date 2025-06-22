package com.bugrahankaramollaoglu.tasty

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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


    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    var loggedInUsername by mutableStateOf<String?>(null)
        private set

    fun resetLoginState() {
        loginState = LoginState.Idle // or some neutral state with no error
    }

    init {
        if (prefManager.isLoggedIn()) {
            loggedInUsername = prefManager.getUsername()
            loginState = LoginState.Success("Welcome back, $loggedInUsername")
        }
    }


    fun isLoggedIn(): Boolean = prefManager.isLoggedIn()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginState = LoginState.Loading
            val result = repository.login(username, password)
            loginState = if (result.isSuccess) {
                prefManager.setLoggedIn(true)
                prefManager.setUsername(username) // Save username here
                loggedInUsername = username  // <-- ADD THIS LINE to update state
                LoginState.Success(result.getOrNull()?.message ?: "Logged in")
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Login failed")
            }
        }
    }

    fun logout() {
        prefManager.setLoggedIn(false)
        loggedInUsername = null
        loginState = LoginState.Idle
    }

}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val message: String) : LoginState()
    data class Error(val error: String) : LoginState()
}