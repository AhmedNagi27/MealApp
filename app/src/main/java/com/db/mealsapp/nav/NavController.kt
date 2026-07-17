package com.db.mealsapp.nav

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.db.mealsapp.date.model.local.MealDB
import com.db.mealsapp.date.repository.DbRepoImpl
import com.db.mealsapp.presentation.screens.FavoriteScreen
import com.db.mealsapp.presentation.screens.MealHomeScreen
import com.db.mealsapp.presentation.screens.MealInfoScreen
import com.db.mealsapp.presentation.screens.SearchScreen
import com.db.mealsapp.presentation.viewmodels.FavoriteViewModel
import com.db.mealsapp.presentation.viewmodels.MealViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Navigation(modifier: Modifier = Modifier, navController: NavHostController) {
  NavHost(navController = navController, startDestination = Routes.HOME, modifier = modifier) {
    val vm = MealViewModel()
    composable(Routes.HOME) {
      MealHomeScreen(
          vm = vm,
          onMealClick = { id -> navController.navigate("${Routes.MEAL_INFO}/$id") },
      )
    }

    composable(Routes.SEARCH) {
      SearchScreen(
          vm = vm,
          onMealClick = { id -> navController.navigate("${Routes.MEAL_INFO}/$id") },
      )
    }

    composable(
        route = "${Routes.MEAL_INFO}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.StringType }),
    ) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id") ?: ""
      MealInfoScreen(
          vm = vm,
          id = id,
          onBack = { navController.popBackStack() },
      )
    }

      composable(Routes.FAVORITE,) {
          val mealDao = MealDB.getDatabase(context = LocalContext.current).mealDao()
          val repo = DbRepoImpl(mealDao)
          val favoriteVm = FavoriteViewModel(repo)
          FavoriteScreen(
              vm = favoriteVm,
              onMealClick = { id -> navController.navigate("${Routes.MEAL_INFO}/$id") },
          )
      }
  }
}
