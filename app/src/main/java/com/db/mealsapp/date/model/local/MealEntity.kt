package com.db.mealsapp.date.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey  val mealId: String,
    val strMeal: String,
    val strMealThumb: String?,
    val isFavorite: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
