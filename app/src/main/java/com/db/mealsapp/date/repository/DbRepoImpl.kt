package com.db.mealsapp.date.repository

import com.db.mealsapp.date.model.local.MealDao
import com.db.mealsapp.date.model.local.MealEntity
import kotlinx.coroutines.flow.Flow

class DbRepoImpl(private val mealDao: MealDao) : DbRepo {
  override suspend fun deleteMeal(mealId: String) {
    mealDao.deleteMeal(mealId)
  }

  override suspend fun insertMeal(meal: MealEntity) {
    mealDao.insertMeal(meal)
  }

  override fun getAllMeals(): Flow<List<MealEntity>> {
    return mealDao.getAllMeals()
  }

  override suspend fun getMealById(mealId: String): MealEntity? {
    return mealDao.getMealById(mealId)
  }

  override fun isFavoriteMeal(mealId: String): Flow<Boolean> {
    return mealDao.isFavoriteMeal(mealId)
  }
}
