package com.bugrahankaramollaoglu.tasty

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


data class LoginRequest(val username: String, val password: String)
data class LoginResponse(
    val message: String,
    val username: String  // Add this
)

interface ApiService {
    @POST("api/login/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}