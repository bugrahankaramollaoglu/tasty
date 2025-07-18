package com.bugrahankaramollaoglu.tasty.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class AddBasketResponse(
    val success: Int, val message: String
)

data class DeleteBasketResponse(
    val success: Int, val message: String
)

// get basket response
@JsonClass(generateAdapter = true)
data class BasketResponse(
    @Json(name = "sepet_yemekler")
    val basketItems: List<FoodInBasket>?,

    @Json(name = "success")
    val success: Int
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

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun deleteFromBasket(
        @Field("sepet_yemek_id") basketItemId: Int,
        @Field("kullanici_adi") username: String,
    ): Response<DeleteBasketResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getBasketItems(
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<ResponseBody>
}

@JsonClass(generateAdapter = true)
data class FoodInBasket(
    @Json(name = "sepet_yemek_id") val basketFoodId: String,
    @Json(name = "yemek_adi") val name: String,
    @Json(name = "yemek_resim_adi") val imageName: String,
    @Json(name = "yemek_fiyat") val price: Int, // TODO inte cevir
    @Json(name = "yemek_siparis_adet") var quantity: Int, // TODO inte cevir
    @Json(name = "kullanici_adi") val username: String
)


