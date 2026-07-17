package com.db.mealsapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.db.mealsapp.R
import com.db.mealsapp.date.model.local.MealEntity

@Composable
fun MealFgridItem(modifier: Modifier = Modifier, meal: MealEntity, onClick: (String) -> Unit) {
  Column(modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick(meal.mealId) })) {
    Box(
        modifier =
            Modifier.fillMaxWidth()
                .aspectRatio(0.67f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E1E1E)),
    ) {
      AsyncImage(
          model = meal.strMealThumb ?: { R.drawable.unavailable },
          contentDescription = meal.strMeal,
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop,
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = meal.strMeal,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(horizontal = 2.dp),
    )
  }
}
