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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

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
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    page.value = page.value - 1
                    viewModel.previousDay()
                }
            ) {
                Text(text = mainState.previousDay)
            }

            Button(
                onClick = {
                    page.value = page.value + 1
                    viewModel.nextDay()
                }
            ) {
                Text(text = mainState.nextDay)
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
