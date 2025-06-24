package com.bugrahankaramollaoglu.tasty.view.app_screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.BasketScreen
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.FavouritesScreen
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.ProfileScreen
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation

/*@Composable
fun HomeScreen(authViewModel: AuthViewModel, navController: NavHostController) {

}*/

@Composable
fun HomeScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    val items = listOf(
        R.drawable.profile,
        R.drawable.favourite,
        R.drawable.basket,
        R.drawable.setting
    )

    val labels = listOf(
        "Profile",
        "Favourite",
        "Basket",
        "Settings"
    )

    // Use rememberSaveable to retain state across configuration changes
    var selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    // Create RioBottomNavItemData for the bottom navigation buttons
    val buttons = items.mapIndexed { index, iconData ->
        RioBottomNavItemData(
            imageVector = ImageVector.vectorResource(iconData),
            selected = index == selectedIndex.intValue,
            onClick = { selectedIndex.intValue = index },
            label = labels[index]
        )
    }

    // Main Scaffold setup
    Scaffold(
        bottomBar = {
            BottomNavigationBar(buttons = buttons)
        },
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->
        // Handle the screen content based on the selected index
        ScreenContent(selectedIndex.intValue, modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun ScreenContent(selectedIndex: Int, modifier: Modifier = Modifier) {
    when (selectedIndex) {
        0 -> ProfileScreen()
        1 -> FavouritesScreen()
        2 -> BasketScreen()
        3 -> SettingsScreen()
    }
}

@Composable
fun BottomNavigationBar(buttons: List<RioBottomNavItemData>) {
    RioBottomNavigation(
        fabIcon = ImageVector.vectorResource(id = R.drawable.food_delivery),
        buttons = buttons,
        fabSize = 70.dp,
        barHeight = 70.dp,
        onFabClick = {

        },
        selectedItemColor = CustomColors.CustomRed,
        fabBackgroundColor = CustomColors.CustomRed,
    )
}

