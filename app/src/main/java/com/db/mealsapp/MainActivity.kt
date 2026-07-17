package com.db.mealsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.db.mealsapp.nav.BottomNavBar
import com.db.mealsapp.nav.Navigation
import com.db.mealsapp.nav.Routes
import com.db.mealsapp.presentation.component.TopBar
import com.db.mealsapp.ui.theme.MealsAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MealsAppTheme(dynamicColor = true) {
        val navController = rememberNavController()
        var pageTitle by remember { mutableStateOf("Explore") }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            topBar = {
              if (currentRoute?.startsWith(Routes.HOME) == true) pageTitle = "Explore"
              else if (currentRoute?.startsWith(Routes.SEARCH) == true) pageTitle = "Search"
              else if (currentRoute?.startsWith(Routes.FAVORITE) == true) pageTitle = "Favorites"
              else if (currentRoute?.startsWith(Routes.MEAL_INFO) == true)
                  pageTitle = "Meal Details"

              TopBar(
                  title = pageTitle,
                  navIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_fastfood_24),
                        contentDescription = null,
                    )
                  },
                  actionsRow = {},
                  modifier = Modifier,
              )
            },
            bottomBar = {
              BottomNavBar(
                  onHomeClick = {
                    navController.navigate(Routes.HOME) {
                      popUpTo(navController.graph.startDestinationId)
                      launchSingleTop = true
                    }
                  },
                  onSearchClick = { navController.navigate(Routes.SEARCH) },
                  onFavoriteClick = { navController.navigate(Routes.FAVORITE) },
              )
            },
        ) { innerPadding ->
          Navigation(
              navController = navController,
              modifier = Modifier.padding(innerPadding),
          )
        }
      }
    }
  }
}
