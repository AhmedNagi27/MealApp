package com.db.mealsapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.db.mealsapp.date.model.local.MealEntity
import com.db.mealsapp.date.repository.DbRepoImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val dbRepo: DbRepoImpl) : ViewModel() {

  val favoriteMeals = dbRepo.getAllMeals()

  private val _meal = MutableLiveData<MealEntity?>()
  val meal: LiveData<MealEntity?> = _meal

  fun insertMeal(meal: MealEntity) {
    viewModelScope.launch { dbRepo.insertMeal(meal) }
  }

  fun deleteMeal(mealId: String) {
    viewModelScope.launch { dbRepo.deleteMeal(mealId) }
  }

  fun getMealById(mealId: String) {
    viewModelScope.launch {
      _meal.value = dbRepo.getMealById(mealId)
    }
  }

  fun isFavoriteMeal(mealId: String): Flow<Boolean> {
    return dbRepo.isFavoriteMeal(mealId)
  }

}
