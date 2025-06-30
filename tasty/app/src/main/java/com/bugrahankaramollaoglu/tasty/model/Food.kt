package com.bugrahankaramollaoglu.tasty.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// food modeli
@JsonClass(generateAdapter = true) // otomatik adapter yaratiyor moshi
data class Food(

    // api'deki adlarla bu adlar aynı olsaydi
    // @json satırlarına gerek kalmayacaktı
    //
    //    { "yemek_id": "2",
    //      "yemek_adi": "Baklava",
    //      "yemek_resim_adi": "baklava.png",
    //      "yemek_fiyat": "250"
    //    },
    //
    @Json(name = "yemek_id") val id: Int,
    @Json(name = "yemek_adi") val name: String,
    @Json(name = "yemek_resim_adi") val imageName: String,
    @Json(name = "yemek_fiyat") val price: Double
)
