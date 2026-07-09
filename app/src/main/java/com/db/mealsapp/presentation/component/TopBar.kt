package com.db.mealsapp.presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navIcon: @Composable () -> Unit,
    actionsRow: @Composable (RowScope) -> Unit,
) {
  CenterAlignedTopAppBar(
      title = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
      modifier = modifier,
      navigationIcon = navIcon,
      actions = actionsRow,
  )
}
