package com.bugrahankaramollaoglu.tasty.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    cornerRadius: Dp = 8.dp,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
            .fillMaxWidth()
            .size(
                width = 30.dp,
                height = 50.dp
            )
    ) {
        Text(text = text)
    }
}

@Composable
fun SocialMediaButton(
    onClick: () -> Unit,
    backgroundColor: Color,
    contentColor: Color,
    icon: @Composable () -> Unit,
    size: Dp = 50.dp // You can customize the size if needed
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = Modifier
            .size(size), // Circular button
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp), // So the icon is centered
        elevation = ButtonDefaults.elevation(4.dp)
    ) {
        icon()
    }
}


