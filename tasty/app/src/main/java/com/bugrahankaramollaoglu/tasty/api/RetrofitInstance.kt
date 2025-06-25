package com.bugrahankaramollaoglu.tasty.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Android emulator!
    private const val BASE_URL = "http://10.0.2.2:8000/"

    // if you are using physical device, run server with:
    // python manage.py runserver 0.0.0.0:8000
    // private const val BASE_URL = "http://192.168.1.24:8000/"


    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}