package com.bugrahankaramollaoglu.tasty.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

// kotlinde bir seyin 'lazily initialized' olması demek
// o seyin yalnız access edildiginde create edilmesi demek

// what is singleton?
// singleton is a design pattern where
// a class has only one instance throughout the app
// mesela bir sınıfın var (internet sunucunu, db baglantını simgeliyor)
// bunun sadece tek bir objesi olsun istiyosun, singleton yaratirsin.
// kotlinde singleton "object" anahtar kelimesiyle yaratılır
//
// object MySingleton {
//    fun log(message: String) {
//        println("LOG: $message")
//    }
// } ya da
//
// object AuthManager {
//    var currentUser: String? = null
//
//    fun login(user: String) {
//        currentUser = user
//        println("User logged in: $user")
//    }
//
//    fun logout() {
//        println("User logged out: $currentUser")
//        currentUser = null
//    }
// }
//
// singletonlar memory leaklere yol acabilir, dikkat et.
// singleton objesine context verme.
//

// singleton class
// it is the network engine of our api calls
object FoodsInstance {

    // moshi json'i kotlin diline ceviren bir kütüphane
    // burada onu ilklendiriyoruz
    val moshi: Moshi? =
        Moshi.Builder().add(KotlinJsonAdapterFactory()) // bu sayede daha kotlinvari calısıyor
            .build()

    // retrofit androidde en sık kullanılan http-clienti
    val retrofit: Retrofit =
        Retrofit.Builder().baseUrl("http://kasimadalan.pe.hu/") // base url for our backend
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // says: "use moshi"
            .build()

    // api cagrıları yaparken kullandıgın obje
    val foodsApi: FoodsApiService = retrofit.create(FoodsApiService::class.java)
    val basketApi: BasketApiService = retrofit.create(BasketApiService::class.java)
}


// appi backende baglayan köprü
object RetrofitInstance {

    // Android emulator!
    private const val BASE_URL = "http://10.0.2.2:8000/"

    // if you are using physical device, run server with:
    // python manage.py runserver 0.0.0.0:8000
    // private const val BASE_URL = "http://192.168.1.24:8000/"

    // by lazy means don't create it untill it is accessed.
    val authService: AuthService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(AuthService::class.java)
    }
}
