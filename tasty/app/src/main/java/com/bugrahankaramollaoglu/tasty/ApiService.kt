package com.bugrahankaramollaoglu.tasty

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(
    val message: String,
    val username: String  // Add this
)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val password2: String
)

data class RegisterResponse(
    val id: Int,
    val username: String,
    val email: String
)

interface ApiService {

    // login
    @POST("api/token/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // register
    @POST("api/register/")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}
