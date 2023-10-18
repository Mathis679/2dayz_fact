package com.example.a2dayzfact.ui.screens.mainlist.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.a2dayzfact.ui.screens.fact.item.FactItem
import com.example.a2dayzfact.ui.screens.fact.item.FactItemPlaceHolder
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainListContent(
    factsUiState: MainListViewModel.FactsUiState,
    currentDay: String,
    shouldAnimateText: Boolean
) {
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxWidth()) {
        AnimatedTitleComponent(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            title = "En ce jour", subtitle = currentDay, shouldAnimateText = shouldAnimateText)
        Surface(color = Color.Transparent) {
            AnimatedVisibility(
                visible =  factsUiState is MainListViewModel.FactsUiState.Success,
                enter = fadeIn(),
                exit = fadeOut(),
                label = ""
            ) {
                (factsUiState as? MainListViewModel.FactsUiState.Success)?.let {
                    HorizontalPager(
                        modifier = Modifier.fillMaxWidth(),
                        pageCount = factsUiState.facts.size,
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 64.dp, vertical = 16.dp)
                    ) { index ->
                        FactItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer {
                                    // Calculate the absolute offset for the current page from the
                                    // scroll position. We use the absolute value which allows us to mirror
                                    // any effects for both directions
                                    val pageOffset = (
                                            (pagerState.currentPage - index) + pagerState
                                                .currentPageOffsetFraction
                                            ).absoluteValue

                                    // We animate the alpha, between 50% and 100%
                                    // Alpha is commented for now because it causes issues with card elevation
//                                    alpha = lerp(
//                                        start = 0.5f,
//                                        stop = 1f,
//                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                                    )

                                    scaleX = lerp(
                                        start = 0.7f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )

                                    scaleY = lerp(
                                        start = 0.7f,
                                        stop = 1f,
                                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                    )
                                },
                            year = factsUiState.facts[index].year,
                            title = factsUiState.facts[index].title,
                            content = factsUiState.facts[index].content,
                            image = factsUiState.facts[index].image
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible =  factsUiState is MainListViewModel.FactsUiState.Loading,
                enter = fadeIn(),
                exit = fadeOut(),
                label = ""
            ) {
                FactItemPlaceHolder(modifier = Modifier.padding(horizontal = 64.dp, vertical = 16.dp))
            }
        }
    }
}
