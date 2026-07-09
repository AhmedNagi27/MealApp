package com.db.mealsapp.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navIcon: @Composable () -> Unit,
    actionsRow: @Composable (RowScope) -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        Color(255,112,67),
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified
    ),
) {
  CenterAlignedTopAppBar(
      title = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
      modifier = modifier,
      navigationIcon = navIcon,
      actions = actionsRow,
      colors = colors
  )
}
