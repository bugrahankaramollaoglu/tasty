package com.bugrahankaramollaoglu.tasty.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

//  ************************ LOGIN *******************************

//bu model gönderecegimiz login credentialsi temsil ediyor
//
//{
//    "username": "bugra",
//    "password": "1234"
//}
data class LoginRequest(
    val username: String,
    val password: String
)

//bu model gelecek responseu temsil ediyor
//
//{
//    "message": "Login successful",
//    "username": "bugra"
//}
data class LoginResponse(
    val message: String,
    val username: String  // Add this
)

//  ************************ REGISTER *******************************


data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val password2: String
)

data class RegisterResponse(
    val id: Int,
    val username: String,
    val email: String
)

// suspend fonksiyonları yalnızca corouite icinden
// ya da bir baska suspend fonksiyon icinden cagirabilirsin
// kotlinde coroutine yaratmak icin
// viewModelScope.launch {} dersin

/* In Kotlin, an interface is like a blueprint that says,
“Hey, I have these functions — someone else will make them work.”
Think of it like a menu in a restaurant. The menu shows the dishes
(functions), but doesn’t cook them — the kitchen (Retrofit) does that.
So AuthService is a menu of API calls your app wants to make.*/
interface AuthService {


    // Your app sends a request to the internet.
    // One of the most common ways to do this is with the HTTP protocol:
    // GET – ask for data (like asking Google for weather)
    // POST – send data (like logging in or registering)
    // @POST("api/token/") means: “Send data using POST to the link.
    // The part "api/token/" is added to the base URL you define
    // elsewhere in Retrofit. like http://yourserver/api/token/
    @POST("api/token/")
    // suspend fonksiyonu coroutine (threadler) yapar.
    // bu sayede bu fonksiyon ARKAPLANDA çalışır.
    // android uygulamalarda bir istek 5 saniyeden fazla
    // zaman alırsa uygulaman donar, suspend fun'lar bunu önler.
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    // register
    @POST("api/register/")
    // buralarda @Body olmasının sebebi: requestininin icerigi (email, sifre)
    // retrofit bunu @Body ile parse ediyor. olmazsa hata veriyor.
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}
