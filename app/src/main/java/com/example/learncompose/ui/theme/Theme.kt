package com.example.learncompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.learncompose.R


val AndroidGreen = Color(0xFF3DDC84)
val AndroidGreenDark = Color(0xFF20B261)
val Orange = Color(0xFFFFA500)
val OrangeDark = Color(0xFFCC8400)

private val DarkColorPalette = darkColorScheme(
    primary = AndroidGreenDark,
    secondary = OrangeDark
)
private val LightColorPalette = lightColorScheme(
    primary = AndroidGreen,
    secondary = Orange
)

@Composable
fun ComposeUnitConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme)
        DarkColorPalette
    else
        LightColorPalette.copy(
            secondary = colorResource(id = R.color.orange_dark)
        )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}