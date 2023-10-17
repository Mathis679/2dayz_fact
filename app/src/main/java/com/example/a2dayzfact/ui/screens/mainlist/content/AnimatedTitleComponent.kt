package com.example.a2dayzfact.ui.screens.mainlist.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedTitleComponent(
    title: String,
    subtitle: String
) {
    val anim = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    Column {
        AnimatedVisibility(
            visibleState = anim,
            enter = slideInHorizontally { fullWidth -> fullWidth }
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
            enter = slideInVertically { height -> height }
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
