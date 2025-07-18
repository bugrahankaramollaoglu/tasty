package com.bugrahankaramollaoglu.tasty.api

import com.bugrahankaramollaoglu.tasty.model.FoodResponse
import retrofit2.http.GET

// retrofit is a famous http client for android

// bu arayüz backendden yemeklerin bilgisini alan bir API
// cagrısını temsil ediyor. bu arayüz sayesinde uygulaman
// internetle iletisime geciyor.
interface FoodsApiService {
    // GET http metoduyla bilgileri aliyor
    @GET("yemekler/tumYemekleriGetir.php")
    // suspend olma sebebi async olmasi
    suspend fun getAllFoods(): FoodResponse
}