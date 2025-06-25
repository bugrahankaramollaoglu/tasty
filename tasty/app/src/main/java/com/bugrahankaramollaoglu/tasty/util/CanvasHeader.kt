package com.bugrahankaramollaoglu.tasty.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun CanvasHeader(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, height * 0.75f)

            // Narrow, deep dip on the left side
            quadraticBezierTo(
                width * 0.15f, height * 1.15f,  // Control point (steep curve down)
                width * 0.45f, height * 0.75f   // End point of first curve
            )

            // Wider, higher wave on the right side
            quadraticBezierTo(
                width * 0.85f, height * 0.25f,  // Control point (high up)
                width, height * 0.6f            // End point
            )

            // Complete the shape
            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }




        drawPath(
            path = path,
            color = CustomColors.CustomYellow // or your custom blue color
        )
    }
}
