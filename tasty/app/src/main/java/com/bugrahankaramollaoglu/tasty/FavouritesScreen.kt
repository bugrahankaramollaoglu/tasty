package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CustomColors

@Composable
fun FavouritesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {
        Column() {
            Image(

                painter = painterResource(id = R.drawable.login_logo),

                contentDescription = null

            )
        }
    }

}