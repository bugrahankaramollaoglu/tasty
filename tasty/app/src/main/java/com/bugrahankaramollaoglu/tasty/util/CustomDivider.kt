package com.bugrahankaramollaoglu.tasty.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomDivider(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 60.dp)
    ) {
        Divider(
            color = CustomColors.CustomWhite2,
            thickness = 2.dp,
            modifier = Modifier
                .weight(1f)
                .shadow(elevation = 2.dp)
        )
        Box {

            Text(

                text = text,

//                fontSize = 50.sp,

                fontWeight = FontWeight.ExtraBold,

                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold
                ),

                fontFamily = myFontJomhuria,

                color = CustomColors.CustomWhite2,   // Kontur rengi

                modifier = Modifier
                    .offset(1.dp, 1.dp)
                    .padding(horizontal = 8.dp)

            )

        }
        /*Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )*/
        Divider(
            color = CustomColors.CustomWhite2,
            thickness = 2.dp,
            modifier = Modifier
                .weight(1f)
                .shadow(elevation = 2.dp)
        )
    }
}
