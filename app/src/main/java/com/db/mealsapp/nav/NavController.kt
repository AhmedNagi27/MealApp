package com.db.mealsapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.db.mealsapp.MealViewModel
import com.db.mealsapp.presentation.screens.MealHomeScreen
import com.db.mealsapp.presentation.screens.MealInfoScreen
import com.db.mealsapp.presentation.screens.SearchScreen

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
      MealInfoScreen(vm = vm, id = id, onBack = { navController.popBackStack() })
    }
  }
}
