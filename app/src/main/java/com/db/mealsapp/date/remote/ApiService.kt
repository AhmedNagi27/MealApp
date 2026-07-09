package com.db.mealsapp.date.remote

import com.db.mealsapp.date.model.remote.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("search.php?")
    suspend fun getMealsList(
        @Query("f") letter: String
    ): MealResponse

    @GET("lookup.php?")
    suspend fun getMealInfo(
        @Query("i") id: String
    ): MealResponse

    @GET("search.php?")
    suspend fun searchMeal(
        @Query("s") query: String
    ): MealResponse
}
