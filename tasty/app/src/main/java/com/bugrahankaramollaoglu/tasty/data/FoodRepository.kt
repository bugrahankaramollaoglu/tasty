package com.bugrahankaramollaoglu.tasty.data

import com.bugrahankaramollaoglu.tasty.model.Food
import com.bugrahankaramollaoglu.tasty.retrofit.FoodsInstance


// hem burada, hem FoodService'de getFoods()/getAllFoods() fonksiyonu var
//
// interface FoodApiService {
//    @GET("yemekler/tumYemekleriGetir.php")
//    suspend fun getAllFoods(): FoodResponse
// }
//
// neden? cünkü yukarıdaki asıl cagrıyı yapan arayüz, low level. söyle ki:
// FoodViewModel'da yani UI tarafinda direkt olarak FoodRepository içinden
// getFoods() cagrılıyor. bu da FoodsInstance'a gidiyor, o da FoodsApiService'e
// gidiyor o da @GET ile json verisini cekiyor url'den, finally.
//
class FoodRepository {
    suspend fun getFoods(): List<Food> {
        return FoodsInstance.foodsApi.getAllFoods().foods
    }
}