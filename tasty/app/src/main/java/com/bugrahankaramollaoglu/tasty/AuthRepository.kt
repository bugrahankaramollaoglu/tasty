package com.bugrahankaramollaoglu.tasty

import android.util.Log
import com.bugrahankaramollaoglu.tasty.api.ApiService
import com.bugrahankaramollaoglu.tasty.api.LoginRequest
import com.bugrahankaramollaoglu.tasty.api.LoginResponse

class AuthRepository(private val api: ApiService) {
    suspend fun login(username: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("AuthRepository", "Login response body: $body")
                Result.success(body!!)
            } else {
                Log.e("AuthRepository", "Login failed: ${response.errorBody()?.string()}")
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Exception during login", e)
            Result.failure(e)
        }
    }
}

