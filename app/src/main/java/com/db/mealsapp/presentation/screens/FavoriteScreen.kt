package com.db.mealsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.db.mealsapp.presentation.component.MealFgridItem
import com.db.mealsapp.presentation.viewmodels.FavoriteViewModel

@Composable
fun FavoriteScreen(
    vm: FavoriteViewModel,
    modifier: Modifier = Modifier,
    onMealClick: (String) -> Unit,
) {
  Box(modifier = modifier) {
    LaunchedEffect(key1 = true) { vm.favoriteMeals }
    val favoriteMeals = vm.favoriteMeals.collectAsState(initial = emptyList())
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      items(favoriteMeals.value.size) { meal ->
        MealFgridItem(
            meal = favoriteMeals.value[meal],
            onClick = { onMealClick(favoriteMeals.value[meal].mealId) },
        )
      }
    }
  }
}
