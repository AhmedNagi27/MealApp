package com.db.mealsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.db.mealsapp.MealViewModel
import com.db.mealsapp.presentation.component.MealGridItem
import com.db.mealsapp.util.UiState

@Composable
fun MealHomeScreen(modifier: Modifier = Modifier, vm: MealViewModel, onMealClick: (String) -> Unit) {

  val state by vm.mealsList.collectAsState(UiState.Loading)
  LaunchedEffect('k') {vm.getMealsListByLetter("k") }
  Column(modifier = modifier
    .fillMaxSize()
    .then(modifier)) {
    val letters = ('A'..'Z').toList()
    LazyRow(modifier = Modifier.fillMaxWidth()) {
      items(letters.size) {
        val l = letters[it].toString()
        Button(
            onClick = { vm.getMealsListByLetter(l) },
            modifier = Modifier
              .size(50.dp)
              .padding(4.dp),
            shape = ButtonDefaults.outlinedShape,
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(255,112,67)
            )
        ) {
          Text(text = letters[it].toString(), textAlign = TextAlign.Center)
        }
      }
    }
    HorizontalDivider(modifier = Modifier
      .fillMaxWidth()
      .height(1.dp))
/*        LazyColumn(modifier = Modifier.fillMaxSize()) {
          when (val uiState = state) {
            is UiState.Loading -> {
              item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                  CircularProgressIndicator()
                }
              }
            }
            is UiState.Error -> {
              item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                  Text(text = "Error: ${uiState.message}")
                }
              }
            }
            is UiState.Success -> {
              val meals = uiState.data.meals ?: emptyList()
              items(meals.size) {
                MealListItem(
                  meal = meals[it],
                ) {}
              }
            }
          }
        }

 */
    Spacer(Modifier.height(4.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      when (val uiState = state) {
        is UiState.Loading -> {
          item(span = { GridItemSpan(maxLineSpan) }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
              CircularProgressIndicator()
            }
          }
        }

        is UiState.Error -> {
          item(span = { GridItemSpan(maxLineSpan) }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
              Text(text = "Error: ${uiState.message}")
            }
          }
        }

        is UiState.Success -> {
          val meals = uiState.data.meals ?: emptyList()
          items(meals.size) { meal -> MealGridItem(meal = meals[meal]!!, onClick = { onMealClick(meals[meal]?.idMeal!!) }) }
        }
      }
    }
  }
}
