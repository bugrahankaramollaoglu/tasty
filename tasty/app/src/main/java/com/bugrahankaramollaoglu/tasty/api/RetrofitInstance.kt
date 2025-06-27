package com.bugrahankaramollaoglu.tasty.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object FoodsInstance {

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://kasimadalan.pe.hu/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: FoodApiService = retrofit.create(FoodApiService::class.java)
}


object RetrofitInstance {


    // Android emulator!
    private const val BASE_URL = "http://10.0.2.2:8000/"

    // if you are using physical device, run server with:
    // python manage.py runserver 0.0.0.0:8000
    // private const val BASE_URL = "http://192.168.1.24:8000/"


    val apiService: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}