package com.example.a2dayzfact.ui.screens.fact.item

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.a2dayzfact.R
import com.example.a2dayzfact.ui.theme.Grey
import com.example.a2dayzfact.ui.theme.GreyClear
import com.example.a2dayzfact.ui.theme.bold16
import com.example.a2dayzfact.ui.theme.bold18
import com.example.a2dayzfact.ui.theme.medium15

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactItem(
    modifier: Modifier = Modifier,
    year: Int,
    title: String,
    content: String,
    image: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Box(modifier = Modifier.height(IntrinsicSize.Min)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.3F),
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)))
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp),
                text = "$year",
                style = bold18(),
                color = MaterialTheme.colorScheme.background
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = title,
            style = bold18(),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = content,
            style = medium15(),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )

        Button(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            onClick = onClick
        ) {
            Text(
                text = stringResource(id = R.string.button_see_more),
                style = bold16(),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun FactItemPlaceHolder(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val alpha = infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    Card(
        modifier = modifier
            .height(250.dp)
            .graphicsLayer {
                this.alpha = alpha.value
            },
        colors = CardDefaults.cardColors(containerColor = GreyClear)
    ) {
        Box(
            modifier = Modifier
                .background(Grey)
                .fillMaxWidth()
                .aspectRatio(23F / 10F)
                .graphicsLayer {
                    this.alpha = alpha.value
                },
        )
    }
}
