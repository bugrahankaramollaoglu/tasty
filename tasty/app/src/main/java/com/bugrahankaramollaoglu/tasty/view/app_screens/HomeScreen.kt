package com.bugrahankaramollaoglu.tasty.view.app_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.FavouritesScreen
import com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens.FoodsScreen
import com.bugrahankaramollaoglu.tasty.viewModel.AuthViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.BasketViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel
import com.bugrahankaramollaoglu.tasty.viewModel.FoodViewModel
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation

@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    foodViewModel: FoodViewModel,
    basketViewModel: BasketViewModel,
    favouriteViewModel: FavouriteViewModel,
    navController: NavHostController
) {
    val items = listOf(
        R.drawable.hamburger,
        R.drawable.favourite,
        R.drawable.basket,
        R.drawable.setting
    )

    val labels = listOf(
        "Foods", "Favourite", "Basket", "Settings"
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

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier.imePadding()
//                    .navigationBarsPadding() // navBar varsa ekle (fiziksel cihazda)
            ) {
                BottomNavigationBar(buttons = buttons, navController)
            }
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Handle the screen content based on the selected index
        ScreenContent(
            selectedIndex.intValue,
            modifier = Modifier.padding(innerPadding),
            foodViewModel,
            authViewModel,
            basketViewModel,
            favouriteViewModel,
            navController,
        )
    }
}

@Composable
fun ScreenContent(
    selectedIndex: Int,
    modifier: Modifier,
    foodViewModel: FoodViewModel,
    authViewModel: AuthViewModel,
    basketViewModel: BasketViewModel,
    favouriteViewModel: FavouriteViewModel,
    navController: NavHostController,
) {
    when (selectedIndex) {
        0 -> FoodsScreen(
            authViewModel,
            favouriteViewModel,
            basketViewModel,
            navController
        ) // foods degistir
        1 -> FavouritesScreen(favouriteViewModel)
        2 -> BasketScreen(navController, basketViewModel, authViewModel, foodViewModel)
        3 -> SettingsScreen(authViewModel, navController)
    }
}

@Composable
fun BottomNavigationBar(buttons: List<RioBottomNavItemData>, navController: NavController) {
    RioBottomNavigation(
        fabIcon = ImageVector.vectorResource(id = R.drawable.food_delivery),
        buttons = buttons,
        fabSize = 70.dp,
        barHeight = 70.dp,
        onFabClick = {
            navController.navigate("courier")
        },
        selectedItemColor = CustomColors.CustomRed,
        fabBackgroundColor = CustomColors.CustomRed,
    )
}

