package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel

@Composable
fun FavouritesScreen(favouriteViewModel: FavouriteViewModel) {

    val favourites = favouriteViewModel.favourites // This is observable now

//    val favouriteViewModel2: FavouriteViewModel = viewModel()


    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(CustomColors.CustomRed)
    ) {
        CanvasHeader()

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                val favourites = favouriteViewModel.favourites
                Log.d("mesaj", "from favouriteScreen: $favourites")

            }) {

        }

        if (favourites.isNullOrEmpty()) {
            Text(text = "favourites boşş", style = TextStyle(fontSize = 40.sp))
        } else {
            LazyColumn {
                items(favourites) { fav ->
                    Text(text = fav.name, style = TextStyle(fontSize = 40.sp))
                }
            }
        }
    }

}