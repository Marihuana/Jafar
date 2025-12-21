package kr.yooreka.jafar.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

private val DarkColorScheme = darkColorScheme(
    primary = CardDefaultDark,
    onPrimary = TextDefaultDark,
    secondary = TagBgDark,
    onSecondary = TagTextDark,
    tertiary = CardBorderDark,
    background = BgDefaultDark,
    surface = CardBorderDark,
    onBackground = TextDefaultDark,
    onSurface = TextDefaultDark,
)

private val LightColorScheme = lightColorScheme(
    primary = CardDefaultLight,
    onPrimary = TextDefaultLight,
    secondary = TagBgLight,
    onSecondary = TagTextLight,
    tertiary = CardBorderLight,
    background = BgDefaultLight,
    surface = BgDefaultLight,
    onBackground = TextDefaultLight,
    onSurface = TextDefaultLight,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun JafarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    fontScaleFactor: Float = 1f,
    dynamicColor: Boolean = false,
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

    val baseDensity = LocalDensity.current
    val scaleDensity = remember(baseDensity, fontScaleFactor) {
        Density(
            density = baseDensity.density,
            fontScale = baseDensity.fontScale * fontScaleFactor
        )
    }

    CompositionLocalProvider(LocalDensity provides scaleDensity) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}