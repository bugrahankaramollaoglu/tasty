package com.bugrahankaramollaoglu.tasty.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    backgroundColor: Color = CustomColors.CustomWhite2,
    contentColor: Color = Color.Black,
    cornerRadius: Dp = 10.dp,
    height: Dp = 50.dp,
    elevation: Dp = 8.dp,               // Added elevation param
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(cornerRadius),
        color = backgroundColor,
        elevation = elevation,
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholder, color = contentColor.copy(alpha = 0.5f))
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = contentColor,
                backgroundColor = Color.White,  // Make background transparent since Surface already has background
                cursorColor = contentColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxSize(),  // Fill the Surface size
            shape = RoundedCornerShape(cornerRadius)
        )
    }
}
