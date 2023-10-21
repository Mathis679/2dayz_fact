package com.example.a2dayzfact.ui.navigation

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
    factDetailViewModel: FactDetailViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = FactNavigationItems.FactMainPage.screenRoute
    ) {
        composable(route = FactNavigationItems.FactMainPage.screenRoute) {
            MainListScreen(viewModel = mainListViewModel) {
                navController.navigate(FactNavigationItems.FactDetailPage.screenRoute)
            }
        }
        composable(route = FactNavigationItems.FactDetailPage.screenRoute) {
            FactDetailScreen(viewModel = factDetailViewModel)
        }
    }
}
