package com.db.mealsapp.presentation.component

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Int,
    color: Color = Color(255, 112, 67),
) {
  OutlinedButton(
      onClick = onClick,
      modifier = modifier,
      colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
      shape = ButtonDefaults.outlinedShape,
  ) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
    )
  }
}
