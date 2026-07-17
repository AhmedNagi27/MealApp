package com.db.mealsapp.date.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MealEntity::class], version = 1)
abstract class MealDB : RoomDatabase() {
  abstract fun mealDao(): MealDao

  companion object {
    @Volatile private var INSTANCE: MealDB? = null

    fun getDatabase(context: Context): MealDB {
      return INSTANCE
          ?: synchronized(this) {
            val instance =
                Room.databaseBuilder(context.applicationContext, MealDB::class.java, "MealDB")
                    .build()
            INSTANCE = instance
            instance
          }
    }
  }
}
