package com.bugrahankaramollaoglu.tasty.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CanvasHeader(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val path = Path().apply {
                moveTo(0f, height * 0.75f)

                quadraticBezierTo(
                    width * 0.25f, height * 1.15f,
                    width * 0.45f, height * 0.75f
                )

                quadraticBezierTo(
                    width * 0.75f, height * 0.05f,
                    width, height * 0.6f
                )

                // Complete the shape
                lineTo(width, 0f)
                lineTo(0f, 0f)
                close()
            }

            drawPath(
                path = path, color = CustomColors.CustomYellow
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Tasty!", style = MaterialTheme.typography.h6.copy(
                    color = CustomColors.CustomBlack,
                    fontFamily = myFontJomhuria,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(top = 40.dp, start = 50.dp)
            )
            Row(
                modifier = Modifier.padding(end = 40.dp, top = 50.dp)
            ) {
                Text(
                    text = "Home", style = MaterialTheme.typography.h6.copy(
                        color = CustomColors.CustomWhite,
                        fontFamily = myFontJomhuria,
                        fontSize = 25.sp,
                    ), modifier = Modifier.padding(top = 7.dp)
                )
                Spacer(Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home Icon",
                    tint = CustomColors.CustomWhite,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { })
            }
        }
    }
}
