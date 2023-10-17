package com.example.a2dayzfact.ui.screens.fact.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun FactItem(
    modifier: Modifier = Modifier,
    year: Int,
    title: String,
    content: String,
    image: String
) {
    Card(
        modifier = modifier.width(200.dp),
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.surface)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = image,
            contentDescription = null
        )

        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2
        )

        Text(
            text = content,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 5
        )

        Button(onClick = {  }) {
            Text(text = "Voir plus")
        }
    }
}
