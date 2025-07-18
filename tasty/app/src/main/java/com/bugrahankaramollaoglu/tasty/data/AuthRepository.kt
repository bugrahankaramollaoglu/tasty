package com.bugrahankaramollaoglu.tasty.data

import android.util.Log
import com.bugrahankaramollaoglu.tasty.api.AuthService
import com.bugrahankaramollaoglu.tasty.api.LoginRequest
import com.bugrahankaramollaoglu.tasty.api.LoginResponse

// retrofit cagrilarini basitlestirir (abstraction)
// viewmodel ile retrofitAPI arasında bir köprü görevi görür
// yani bilgi şöyle akar:
// djangoBE -> authService (retrofitAPI) -> authRepository -> AuthViewModel -> UI
// peki neden login islemini authVM'da da yapiyoruz, burada login()'i wrap ediyoruz?
// cünkü eger AuthRepo tanımlamasaydın,
// You’d have to handle networking, exceptions, Result, API calls, and JSON parsing
// inside the ViewModel.
class AuthRepository(private val api: AuthService) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            // login girisiminin sonucunu tutuyor
            val response = api.login(LoginRequest(username, password))

            if (response.isSuccessful) {
                val body = response.body()
                Log.d("mesaj", "Login response body: $body")
                Result.success(body!!)
            } else {
                Log.e("mesaj", "Login failed: ${response.errorBody()?.string()}")
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Log.e("mesaj", "Exception during login", e)
            Result.failure(e)
        }
    }
}