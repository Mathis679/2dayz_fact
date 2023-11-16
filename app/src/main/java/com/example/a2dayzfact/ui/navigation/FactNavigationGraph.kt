package com.example.a2dayzfact.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a2dayzfact.ui.screens.fact.content.FactDetailScreen
import com.example.a2dayzfact.ui.screens.fact.content.FactDetailViewModel
import com.example.a2dayzfact.ui.screens.mainlist.content.MainListScreen
import com.example.a2dayzfact.ui.screens.mainlist.content.MainListViewModel

@Composable
fun FactNavigationGraph(
    navController: NavHostController,
    mainListViewModel: MainListViewModel = hiltViewModel(),
    factDetailViewModel: FactDetailViewModel = hiltViewModel(),
    goToSource: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = FactNavigationItems.FactMainPage.screenRoute
    ) {
        composable(
            route = FactNavigationItems.FactMainPage.screenRoute
        ) {
            MainListScreen(viewModel = mainListViewModel) {
                navController.navigate(FactNavigationItems.FactDetailPage.screenRoute) {
                    launchSingleTop = true
                }
            }
        }
        composable(
            route = FactNavigationItems.FactDetailPage.screenRoute,
            enterTransition = {
                scaleIn(initialScale = 0.5f) + fadeIn()
            },
            exitTransition = {
                scaleOut(targetScale = 0.5f) + fadeOut()
            }
        ) {
            FactDetailScreen(viewModel = factDetailViewModel) {
                goToSource(it)
            }
        }
    }
}
