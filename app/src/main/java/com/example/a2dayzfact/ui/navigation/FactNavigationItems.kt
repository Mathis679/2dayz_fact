package com.example.a2dayzfact.ui.navigation

sealed class FactNavigationItems(var screenRoute: String) {
    object FactMainPage: FactNavigationItems("factMainPage")
    object FactDetailPage: FactNavigationItems("factDetailPage")
}
