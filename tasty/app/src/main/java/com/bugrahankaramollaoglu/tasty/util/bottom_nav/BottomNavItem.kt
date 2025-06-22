package com.bugrahankaramollaoglu.tasty.util.bottom_nav

sealed class BottomNavItem(val route: String, val label: String) {
    object Home : BottomNavItem("home", "Home")
    object Favorites : BottomNavItem("favorites", "Favorites")
    object Profile : BottomNavItem("profile", "Profile")
}

