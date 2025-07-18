package com.bugrahankaramollaoglu.tasty.view.app_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bugrahankaramollaoglu.tasty.R
import com.bugrahankaramollaoglu.tasty.util.CanvasHeader
import com.bugrahankaramollaoglu.tasty.util.CustomColors
import com.bugrahankaramollaoglu.tasty.util.myFontJomhuria
import com.bugrahankaramollaoglu.tasty.util.myFontWhoa

@Composable
fun CheckoutScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColors.CustomRed),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CanvasHeader()


        Spacer(modifier = Modifier.weight(4f))

        Image(
            painter = painterResource(R.drawable.checkout_success),
            contentDescription = "Checkout Success"
        )

        Spacer(modifier = Modifier.weight(2f))


        Text(
            text = "SUCCESS",
            style = TextStyle(
                fontFamily = myFontWhoa,
                fontSize = 50.sp,
                color = CustomColors.CustomYellow,
            )
        )

        Spacer(modifier = Modifier.weight(2f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                colorFilter = ColorFilter.tint(CustomColors.CustomYellow),
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.courier_path_1),
                contentDescription = "Courier path 1"
            )
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.courier_coming),
                contentDescription = "Courier coming"
            )
            Image(
                colorFilter = ColorFilter.tint(CustomColors.CustomYellow),
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.courier_path_2),
                contentDescription = "Courier path 2"
            )
        }

        Spacer(modifier = Modifier.weight(5f))

        Button(
            onClick = {

                navController.navigate("home")

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CustomColors.CustomYellow,
                contentColor = Color.White
            )
        ) {
            androidx.compose.material.Text(
                text = "Go back", style = TextStyle(
                    fontSize = 35.sp,
                    fontFamily = myFontJomhuria,
                    color = CustomColors.CustomBlack,
                )
            )
        }


        Spacer(modifier = Modifier.weight(4f))

    }
}