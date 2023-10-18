package com.example.a2dayzfact.ui.screens.mainlist.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.a2dayzfact.ui.theme.medium15

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainListScreen(
    viewModel: MainListViewModel = hiltViewModel()
) {
    val mainState = viewModel.mainListUiState.collectAsStateWithLifecycle().value
    val listState = viewModel.factsUiState.collectAsStateWithLifecycle().value
    val page = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(top = 24.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    page.value = page.value - 1
                    viewModel.previousDay()
                },
                shape = RoundedCornerShape(topStartPercent = 0, bottomStartPercent = 0, topEndPercent = 50, bottomEndPercent = 50)
            ) {
                Text(
                    text = mainState.previousDay,
                    style = medium15(),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Button(
                onClick = {
                    page.value = page.value + 1
                    viewModel.nextDay()
                },
                shape = RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50, topEndPercent = 0, bottomEndPercent = 0)
            ) {
                Text(
                    text = mainState.nextDay,
                    style = medium15(),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        
        AnimatedContent(
            targetState = page.value,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { width -> width } with slideOutHorizontally { width -> -width }
                } else {
                    slideInHorizontally { width -> -width } with slideOutHorizontally { width -> width }
                }.using(
                    SizeTransform(clip = false)
                )
            },
            label = ""
        ) {
            MainListContent(
                factsUiState = listState,
                currentDay = mainState.currentDay,
                shouldAnimateText = !this.transition.isRunning
            )
        }
    }
}
