package com.bugrahankaramollaoglu.tasty.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class AddBasketResponse(
    val success: Int, val message: String
)

interface BasketApiService {

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun addFoodToBasket(
        @Field("yemek_adi") foodName: String,
        @Field("yemek_resim_adi") foodImageName: String,
        @Field("yemek_fiyat") foodPrice: Int,
        @Field("yemek_siparis_adet") foodQuantity: Int,
        @Field("kullanici_adi") username: String,
    ): Response<AddBasketResponse>

}

