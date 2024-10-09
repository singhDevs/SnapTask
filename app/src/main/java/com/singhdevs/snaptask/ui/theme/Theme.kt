package com.singhdevs.snaptask.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

data class SnapTaskColors(
    val yellowTaskCard: Color,
    val grayTaskCard: Color,
    val greenTaskCard: Color,
    val yellowTaskCardBorder: Color,
    val grayTaskCardBorder: Color,
    val greenTaskCardBorder: Color,
    val titleText: Color,
    val subtitleText: Color,
    val chipSelectedBackground: Color,
    val chipBackground: Color,
    val chipBorder: Color,
    val chipText: Color
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    /*Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),*/

)

// Custom SnapTask Colors
private val LightSnapTaskColors = SnapTaskColors(
    yellowTaskCard = Color(0xFFFFF9C4),
    grayTaskCard = Color(0xFFE0E0E0),
    greenTaskCard = Color(0xFFC8E6C9),
    yellowTaskCardBorder = Color(0xFFFBC02D),
    grayTaskCardBorder = Color(0xFFBDBDBD),
    greenTaskCardBorder = Color(0xFF388E3C),
    titleText = Color(0xFF212121),
    subtitleText = Color(0xFF757575),
    chipSelectedBackground = Color(0xFF70ACEA),
    chipBackground = Color(0xFFBBDEFB),
    chipBorder = Color(0xFF2196F3),
    chipText = Color(0xFF4F4E4E)
)

private val DarkSnapTaskColors = SnapTaskColors(
    yellowTaskCard = Color(0xFFFBC02D),
    grayTaskCard = Color(0xFF424242),
    greenTaskCard = Color(0xFF4CAF50),
    yellowTaskCardBorder = Color(0xFFEEE082),
    grayTaskCardBorder = Color(0xFF757575),
    greenTaskCardBorder = Color(0xFF81C784),
    titleText = Color(0xFFFFFFFF),
    subtitleText = Color(0xFFB0BEC5),
    chipSelectedBackground = Color(0xFF357AE7),
    chipBackground = Color(0xFF0D47A1),
    chipBorder = Color(0xFF90CAF9),
    chipText = Color.White
)
val LocalSnapTaskColors = compositionLocalOf { LightSnapTaskColors }

@Composable
fun SnapTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val snapTaskColors = if (darkTheme) DarkSnapTaskColors else LightSnapTaskColors

    CompositionLocalProvider(LocalSnapTaskColors provides snapTaskColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}