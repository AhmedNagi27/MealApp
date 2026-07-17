package com.db.mealsapp.date.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertMeal(meal: MealEntity)

  @Query("DELETE FROM meals WHERE mealId = :mealId") suspend fun deleteMeal(mealId: String)

  @Query("SELECT * FROM meals ORDER BY timestamp DESC ") fun getAllMeals(): Flow<List<MealEntity>>

  @Query("SELECT * FROM meals WHERE mealId = :mealId")
  suspend fun getMealById(mealId: String): MealEntity?

  @Query("SELECT isFavorite FROM meals WHERE mealId = :mealId")
  fun isFavoriteMeal(mealId: String): Flow<Boolean>
}
