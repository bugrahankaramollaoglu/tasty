package com.bugrahankaramollaoglu.tasty.view.app_screens.BottomNavScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.model.FavouriteItemCard
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.util.myFontWhoa
import com.bugrahankaramollaoglu.tasty.viewModel.FavouriteViewModel

@Composable
fun FavouritesScreen(favouriteViewModel: FavouriteViewModel) {

    val favourites = favouriteViewModel.favourites // This is observable now

//    val favouriteViewModel2: FavouriteViewModel = viewModel()


    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(CustomColors.CustomRed)
    ) {
        CanvasHeader()

        Spacer(Modifier.height(10.dp))

        if (favourites.isNullOrEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.empty_basket),
                    contentDescription = "Favourites is empty",
                    modifier = Modifier
                        .size(400.dp)  // sets width and height to 200dp
                        .padding(horizontal = 16.dp)
                )

                Text(
                    "NO\nFAVOURITE", style = TextStyle(

                        fontFamily = myFontWhoa,
                        color = CustomColors.CustomYellow,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center

                    )
                )
            }
        } else {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(10.dp))

                Text(
                    text = "Favourite Foods", style = TextStyle(
                        fontFamily = myFontJomhuria,
                        fontSize = 50.sp,
                        color = CustomColors.CustomWhite2
                    )
                )

                Spacer(Modifier.height(20.dp))

                LazyColumn {
                    items(favourites) { fav ->
                        FavouriteItemCard(
                            fav, favouriteViewModel
                        )
                    }
                }
            }
        }
    }

}

