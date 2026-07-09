package com.db.mealsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.db.mealsapp.date.model.remote.Meal
import com.db.mealsapp.date.model.remote.MealResponse
import com.db.mealsapp.date.repository.*
import com.db.mealsapp.util.UiState
import kotlinx.coroutines.launch

class MealViewModel(private val repo: MealRepo = MealRepoImpl()) : ViewModel() {
  private val _mealsList = MutableLiveData<UiState<MealResponse>>()
  val mealsList = _mealsList.asFlow()
  private val _mealInfo = MutableLiveData<UiState<Meal>>()
  val mealInfo = _mealInfo.asFlow()

  private val _searchQuery = MutableLiveData<UiState<MealResponse>>()
  val searchQuery = _searchQuery.asFlow()

  fun getMealsListByLetter(letter: String) {
    viewModelScope.launch {
      _mealsList.value = UiState.Loading
      _mealsList.value =
          try {
            repo.getMealsListByLetter(letter)
          } catch (e: Exception) {
            UiState.Error(e.message.toString())
          }
    }
  }

  fun getMealInfoById(id: String) {
    viewModelScope.launch {
      _mealInfo.value = UiState.Loading
      _mealInfo.value =
          try {
            repo.getMealInfoById(id)
          } catch (e: Exception) {
            UiState.Error(e.message.toString())
          }
    }
  }

  fun searchMeal(query: String) {
    viewModelScope.launch {
      _searchQuery.value = UiState.Loading
      _searchQuery.value =
          try {
            repo.searchMeal(query)
          } catch (e: Exception) {
            UiState.Error(e.message.toString())
          }
    }
  }
}
