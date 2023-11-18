package com.mathislaurent.a2dayzfact.ui.screens.fact.content

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FactDetailScreen(
    viewModel: FactDetailViewModel = hiltViewModel(),
    goToSource: (String) -> Unit
) {
    viewModel.loadPage()
    val factState = viewModel.factUiState.collectAsStateWithLifecycle().value

    when (factState) {
        is FactDetailViewModel.FactUiState.Success -> {
            FactDetailContent(
                image = factState.fact.image,
                title = factState.fact.title,
                titleDate = factState.displayDate,
                content = factState.fact.content,
                goToSource = {
                    goToSource(factState.fact.source)
                }
            )
        }
        else -> {
           // do nothing
        }
    }
}
