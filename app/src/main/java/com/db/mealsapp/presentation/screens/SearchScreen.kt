package com.db.mealsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.db.mealsapp.presentation.viewmodels.MealViewModel
import com.db.mealsapp.R
import com.db.mealsapp.presentation.component.MealGridItem
import com.db.mealsapp.util.UiState
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier, vm: MealViewModel, onMealClick: (String) -> Unit) {
  val state by vm.searchQuery.collectAsState(UiState.Loading)
  var query by remember { mutableStateOf("") }
  LaunchedEffect(query) {
    if (query.isNotEmpty()) {
      delay(500.milliseconds)
      vm.searchMeal(query)
    }
  }
  Column(modifier = modifier) {
    TextField(
        modifier = Modifier.fillMaxWidth().padding(4.dp).clip(RoundedCornerShape(32.dp)),
        value = query,
        onValueChange = {
          query = it
          //          vm.searchMeal(query)
        },
        leadingIcon = {
          Icon(
              painter = painterResource(id = R.drawable.baseline_search_24),
              contentDescription = "Search",
          )
        },
        placeholder = { Text("Search...") },
        maxLines = 1,
    )
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
              //               CircularProgressIndicator()
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
          items(meals.size) { meal ->
            MealGridItem(meal = meals[meal]!!, onClick = { onMealClick(meals[meal]?.idMeal!!) })
          }
        }
      }
    }
  }
}
