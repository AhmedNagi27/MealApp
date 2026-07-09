package com.db.mealsapp.date.repository

import com.db.mealsapp.date.model.remote.Meal
import com.db.mealsapp.date.model.remote.MealResponse
import com.db.mealsapp.util.UiState

interface MealRepo {
    suspend fun getMealsListByLetter(letter: String): UiState<MealResponse>
    suspend fun getMealInfoById(id: String): UiState<Meal>
    suspend fun searchMeal(query: String): UiState<MealResponse>
}

