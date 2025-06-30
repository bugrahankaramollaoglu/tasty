package com.bugrahankaramollaoglu.tasty.data

import com.bugrahankaramollaoglu.tasty.api.FoodsInstance
import com.bugrahankaramollaoglu.tasty.model.FoodNetworkItem


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
    suspend fun getFoods(): List<FoodNetworkItem> {
        return FoodsInstance.api.getAllFoods().foods
    }
}