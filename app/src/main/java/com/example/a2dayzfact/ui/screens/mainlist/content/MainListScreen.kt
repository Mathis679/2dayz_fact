package com.example.a2dayzfact.ui.screens.mainlist.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.a2dayzfact.ui.screens.fact.item.FactItem
import kotlin.math.absoluteValue

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainListScreen(
    viewModel: MainListViewModel = hiltViewModel()
) {
    val mainState = viewModel.mainListUiState.collectAsStateWithLifecycle().value
    val listState = viewModel.factsUiState.collectAsStateWithLifecycle().value
    val page = remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
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
            Column(modifier = Modifier.fillMaxWidth()) {
                if (!this@AnimatedContent.transition.isRunning) {
                    AnimatedTitleComponent(title = "En ce jour", subtitle = mainState.currentDay)
                }
                when (listState) {
                    is MainListViewModel.FactsUiState.Success -> {
                        HorizontalPager(
                            modifier = Modifier.fillMaxWidth(),
                            pageCount = listState.facts.size,
                            state = pagerState,
                            pageSpacing = 16.dp,
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            pageSize = PageSize.Fixed(232.dp)

                        ) { index ->
                            FactItem(
                                modifier = Modifier.graphicsLayer {
                                    // Calculate the absolute offset for the current page from the
                                    // scroll position. We use the absolute value which allows us to mirror
                                    // any effects for both directions
                                    val pageOffset = (
                                            (pagerState.currentPage - index) + pagerState
                                                .currentPageOffsetFraction
                                            ).absoluteValue

                                    // We animate the alpha, between 50% and 100%
                                    alpha = lerp(
                                        start = 0.5f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                                },
                                year = listState.facts[index].year,
                                title = listState.facts[index].title,
                                content = listState.facts[index].content,
                                image = listState.facts[index].image
                            )
                        }
                    }
                    else -> {
                        // do nothing
                    }
                }

            }
        }
    }
}
