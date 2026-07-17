package com.db.mealsapp.date.repository

import com.db.mealsapp.date.model.local.MealEntity
import kotlinx.coroutines.flow.Flow

interface DbRepo {
  suspend fun insertMeal(meal: MealEntity)

  suspend fun deleteMeal(mealId: String)

  fun getAllMeals(): Flow<List<MealEntity>>

  suspend fun getMealById(mealId: String): MealEntity?

  fun isFavoriteMeal(mealId: String): Flow<Boolean>
}
