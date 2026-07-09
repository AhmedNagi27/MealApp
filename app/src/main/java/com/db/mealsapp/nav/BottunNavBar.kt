package com.db.mealsapp.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.db.mealsapp.R

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
  NavigationBar(modifier = modifier, tonalElevation = 8.dp) {
    NavigationBarItem(
        selected = true,
        onClick = onHomeClick,
        icon = {
          Icon(
              painter = painterResource(id = R.drawable.outline_home_24),
              contentDescription = "Home",
              tint = Color(255, 112, 67),
          )
        },
    )
    NavigationBarItem(
        selected = true,
        onClick = onSearchClick,
        icon = {
          Icon(
              painter = painterResource(id = R.drawable.baseline_search_24),
              contentDescription = "Search",
              tint = Color(255, 112, 67),
          )
        },
    )
    NavigationBarItem(
        selected = true,
        onClick = onFavoriteClick,
        icon = {
          Icon(
              painter = painterResource(id = R.drawable.outline_favorite_24),
              contentDescription = "Favorites",
              tint = Color(255, 112, 67),
          )
        },
    )
  }
}
