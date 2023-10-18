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
import androidx.compose.ui.Alignment
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
    val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2)
    Column(modifier = Modifier.fillMaxWidth()) {
        AnimatedTitleComponent(title = "En ce jour", subtitle = currentDay, shouldAnimateText = shouldAnimateText)
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
                        pageCount = Int.MAX_VALUE,
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 64.dp)
                    ) { index ->
                        val page = index % factsUiState.facts.size
                        FactItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .graphicsLayer {
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
                            year = factsUiState.facts[page].year,
                            title = factsUiState.facts[page].title,
                            content = factsUiState.facts[page].content,
                            image = factsUiState.facts[page].image
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
                FactItemPlaceHolder(modifier = Modifier.padding(horizontal = 64.dp))
            }
        }
    }
}
