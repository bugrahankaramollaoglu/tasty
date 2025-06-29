package com.bugrahankaramollaoglu.tasty.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// gelen "yemekler" api tablosunu temsil ediyor
//
// {
//    "yemekler": [
//    {
//        "yemek_id": "1",
//        "yemek_adi": "Ayran",
//        "yemek_resim_adi": "ayran.png",
//        "yemek_fiyat": "30"
//    },
//    {
//        "yemek_id": "2",
//        "yemek_adi": "Baklava",
//        "yemek_resim_adi": "baklava.png",
//        "yemek_fiyat": "250"
//    },
//    (...)
//   ],
//   "success": 1
// }
//
// moshi bu tabloyu parse edecek ve bu apinin elemanlarıyla
// bir 'foods' listesi oluşturacak
// @JsonClass satırı sayesinde Moshi custom adapter yazmadan
// otomatik olarak bir adapter yaratacak ve bu adapter json'i
// kotlin objelerine otomatik olarak çevirecek (FoodResponseJsonAdapter gibi)
//
@JsonClass(generateAdapter = true)
data class FoodResponse(
    // Moshi now understands that the JSON "yemekler" corresponds
    // to the Kotlin property "foods".
    @Json(name = "yemekler")
    val foods: List<FoodNetworkItem>
)


@JsonClass(generateAdapter = true)
data class FoodNetworkItem(

    // api'deki adlarla bu adlar aynı olsaydi
    // @json satırlarına gerek kalmayacaktı
    @Json(name = "yemek_id")
    val id: Int,
    @Json(name = "yemek_adi")
    val name: String,
    @Json(name = "yemek_resim_adi")
    val imageName: String,
    @Json(name = "yemek_fiyat")
    val price: Double
)
