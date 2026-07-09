package com.db.mealsapp.date.repository

import com.db.mealsapp.date.model.remote.Meal
import com.db.mealsapp.date.model.remote.MealResponse
import com.db.mealsapp.date.remote.RetrofitInstance
import com.db.mealsapp.util.UiState

class MealRepoImpl : MealRepo {
  override suspend fun getMealsListByLetter(letter: String): UiState<MealResponse> {
    return try {
      val response = RetrofitInstance.apiService.getMealsList(letter)
      UiState.Success(response)
    } catch (e: Exception) {
      UiState.Error(e.message ?: "Unknown error")
    }
  }

  override suspend fun getMealInfoById(id: String): UiState<Meal> {
    return try {
      val response = RetrofitInstance.apiService.getMealInfo(id)
      val meal = response.meals?.firstOrNull() // Get the first meal from the list
      if (meal != null) UiState.Success(meal) else UiState.Error("Meal not found")
    } catch (e: Exception) {
      UiState.Error(e.message ?: "Unknown error")
    }
  }

  override suspend fun searchMeal(query: String): UiState<MealResponse> {
    return try {
      val response = RetrofitInstance.apiService.searchMeal(query)
      UiState.Success(response)
    } catch (e: Exception) {
      UiState.Error(e.message ?: "Unknown error")
    }
  }
}
