package com.bugrahankaramollaoglu.tasty.data

import com.bugrahankaramollaoglu.tasty.api.FoodsInstance
import com.bugrahankaramollaoglu.tasty.model.FoodNetworkItem

class FoodRepository {
    suspend fun getFoods(): List<FoodNetworkItem> {
        return FoodsInstance.api.getAllFoods().foods
    }
}