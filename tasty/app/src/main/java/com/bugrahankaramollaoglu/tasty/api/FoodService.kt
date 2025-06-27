package com.bugrahankaramollaoglu.tasty.api

import com.bugrahankaramollaoglu.tasty.model.FoodResponse
import retrofit2.http.GET

interface FoodApiService {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods(): FoodResponse
}