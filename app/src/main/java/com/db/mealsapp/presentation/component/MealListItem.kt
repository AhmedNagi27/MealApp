package com.db.mealsapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.db.mealsapp.R
import com.db.mealsapp.date.model.remote.Meal

@Composable
fun MealListItem(
    meal: Meal?,
    onClick: () -> Unit,
) {
    Card(onClick = onClick,modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(70.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        ListItem(
            headlineContent = {
                Text(text =meal?.strMeal?:"Missing", style = MaterialTheme.typography.headlineSmall,maxLines = 1, overflow = TextOverflow.Ellipsis)
            },
            supportingContent = {Text(text ="Category: ${meal?.strCategory?:"Missing"}", style = MaterialTheme.typography.titleSmall)},
            leadingContent = {
                AsyncImage(
                    modifier = Modifier.size(48.dp),
                    model = meal?.strMealThumb?: { R.drawable.unavailable },
                    contentDescription = meal?.strMeal?:"Missing",
                    contentScale = ContentScale.Crop,
                )
            },
        )
    }
}

//@Preview
//@Composable
//private fun ItemPrev() {
//    MealListItem(title = "Beef and Mustard Pie", category = "Beef", image = "", onClick = {})
//}

