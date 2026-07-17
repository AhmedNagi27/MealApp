package com.db.mealsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.db.mealsapp.R
import com.db.mealsapp.date.model.local.MealDB
import com.db.mealsapp.date.model.local.MealEntity
import com.db.mealsapp.date.repository.DbRepoImpl
import com.db.mealsapp.presentation.component.SmallButton
import com.db.mealsapp.presentation.viewmodels.FavoriteViewModel
import com.db.mealsapp.presentation.viewmodels.MealViewModel
import com.db.mealsapp.util.UiState

@Composable
fun MealInfoScreen(
    modifier: Modifier = Modifier,
    vm: MealViewModel,
    id: String,
    onBack: () -> Unit,
    favoriteVm: FavoriteViewModel =
        FavoriteViewModel(DbRepoImpl(MealDB.getDatabase(context = LocalContext.current).mealDao())),
) {

  val state by vm.mealInfo.collectAsState(UiState.Loading)

  LaunchedEffect(key1 = id) { vm.getMealInfoById(id) }

  var selectedTab by remember { mutableStateOf("Ingredients") }

  val isFavorite by favoriteVm.isFavoriteMeal(id).collectAsState(initial = false)
  Scaffold(
      modifier = modifier,
  ) {
    Box(modifier = Modifier) {
      Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier =
              Modifier.fillMaxWidth()
                  .zIndex(3f)
                  .padding(horizontal = 8.dp)
                  .align(Alignment.TopCenter),
      ) {
        SmallButton(onClick = onBack, icon = R.drawable.outline_arrow_back_24)
        SmallButton(
            onClick = {
              if (state is UiState.Success) {
                val meal = (state as UiState.Success).data
                if (isFavorite) {
                  favoriteVm.deleteMeal(meal.idMeal!!)
                } else {
                  favoriteVm.insertMeal(
                      MealEntity(
                          mealId = meal.idMeal!!,
                          strMeal = meal.strMeal!!,
                          strMealThumb = meal.strMealThumb,
                          isFavorite = true,
                      )
                  )
                }
              }
            },
            icon =
                if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.outline_favorite_24,
        )
      }
      LazyColumn(modifier = Modifier.padding(it).align(Alignment.TopCenter)) {
        when (val uiState = state) {
          is UiState.Loading -> {
            item {
              Box(
                  modifier = Modifier.fillMaxSize().zIndex(2f),
                  contentAlignment = Alignment.Center,
              ) {
                CircularProgressIndicator()
              }
            }
          }

          is UiState.Error -> {
            item {
              Box(
                  modifier = Modifier.fillMaxSize().zIndex(2f),
                  contentAlignment = Alignment.Center,
              ) {
                Text(text = "Error: ${uiState.message}")
              }
            }
          }

          is UiState.Success -> {
            val meal = uiState.data
            val ingredients =
                listOf(
                    meal.strIngredient1 to meal.strMeasure1,
                    meal.strIngredient2 to meal.strMeasure2,
                    meal.strIngredient3 to meal.strMeasure3,
                    meal.strIngredient4 to meal.strMeasure4,
                    meal.strIngredient5 to meal.strMeasure5,
                    meal.strIngredient6 to meal.strMeasure6,
                    meal.strIngredient7 to meal.strMeasure7,
                    meal.strIngredient8 to meal.strMeasure8,
                    meal.strIngredient9 to meal.strMeasure9,
                    meal.strIngredient10 to meal.strMeasure10,
                    meal.strIngredient11 to meal.strMeasure11,
                    meal.strIngredient12 to meal.strMeasure12,
                    meal.strIngredient13 to meal.strMeasure13,
                    meal.strIngredient14 to meal.strMeasure14,
                    meal.strIngredient15 to meal.strMeasure15,
                    meal.strIngredient16 to meal.strMeasure16,
                    meal.strIngredient17 to meal.strMeasure17,
                    meal.strIngredient18 to meal.strMeasure18,
                    meal.strIngredient19 to meal.strMeasure19,
                    meal.strIngredient20 to meal.strMeasure20,
                )

            item {
              Box(
                  modifier = Modifier.fillMaxSize(),
              ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f).zIndex(1f),
                    model = meal.strMealThumb ?: { R.drawable.unavailable },
                    contentDescription = meal.strMeal ?: "Missing",
                    contentScale = ContentScale.FillBounds,
                )
              }
              Card(
                  modifier = Modifier.fillMaxWidth().offset(y = (-24).dp).zIndex(2f),
                  shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                  elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
              ) {
                Column(modifier = Modifier.padding(8.dp)) {
                  Text(
                      text = (meal.strMeal ?: "Missing"),
                      modifier = Modifier,
                      style = MaterialTheme.typography.headlineLarge,
                  )
                  Row(
                      horizontalArrangement = Arrangement.SpaceBetween,
                      modifier = Modifier.fillMaxWidth().padding(8.dp),
                  ) {
                    if (!meal.strCategory.isNullOrEmpty())
                        Text(
                            text = "Category: ${meal.strCategory}",
                            modifier = Modifier,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    if (!meal.strArea.isNullOrEmpty())
                        Text(
                            text = " ${meal.strArea + " " + "Food"}",
                            modifier = Modifier,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                  }
                  Card(
                      modifier = Modifier.fillMaxWidth().padding(8.dp),
                      shape = RoundedCornerShape(28.dp),
                      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                  ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                      Button(
                          onClick = { selectedTab = "Ingredients" },
                          modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                          colors =
                              ButtonDefaults.buttonColors(
                                  containerColor =
                                      if (selectedTab == "Ingredients") Color(255, 112, 67)
                                      else Color(102, 102, 102),
                              ),
                          shape = RoundedCornerShape(28.dp),
                      ) {
                        Text(text = "Ingredients")
                      }
                      Button(
                          onClick = { selectedTab = "Instructions" },
                          modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                          colors =
                              ButtonDefaults.buttonColors(
                                  containerColor =
                                      if (selectedTab == "Instructions") Color(255, 112, 67)
                                      else Color(102, 102, 102),
                              ),
                          shape = RoundedCornerShape(28.dp),
                      ) {
                        Text(text = "Instructions")
                      }
                    }
                  }
                  Column {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(0.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    ) {
                      when (selectedTab) {
                        "Ingredients" -> {
                          Column {
                            for (ingredient in ingredients.indices) {
                              val ingredient = ingredients[ingredient]
                              if (
                                  !ingredient.first.isNullOrEmpty() &&
                                      !ingredient.second.isNullOrEmpty()
                              ) {
                                Text(
                                    text = "${ingredient.second} Of ${ingredient.first}",
                                    modifier = Modifier.padding(8.dp),
                                )
                              }
                            }
                          }
                        }
                        "Instructions" -> {
                          Text(
                              text = meal.strInstructions ?: "Missing",
                              modifier = Modifier,
                          )
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
