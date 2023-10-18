package com.example.a2dayzfact.ui.screens.mainlist.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun AnimatedTitleComponent(
    title: String,
    subtitle: String,
    shouldAnimateText: Boolean
) {
    val anim = remember { MutableTransitionState(false) }

    LaunchedEffect(shouldAnimateText) {
        if (shouldAnimateText) {
            delay(100)
            anim.targetState = true
        }
    }

    Column(modifier = Modifier.height(150.dp)) {
        AnimatedVisibility(
            visibleState = anim,
            enter = slideInHorizontally { fullWidth -> fullWidth } + fadeIn()
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        AnimatedVisibility(
            visibleState = anim,
            enter = slideInVertically { height -> height } + fadeIn()
        ) {
            Text(
                text = subtitle,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
