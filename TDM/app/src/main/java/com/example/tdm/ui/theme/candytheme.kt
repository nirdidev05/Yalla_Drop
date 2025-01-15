package com.example.tdm.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
class candytheme {


    // Candy-themed colors
    val CandyPink = Color(0xFFFF85A1)
    val SoftPurple = Color(0xFFE0BBE4)
    val BubbleGumPink = Color(0xFFFFB5C2)
    val MintGreen = Color(0xFF98E4C9)
    val CreamsicleOrange = Color(0xFFFFB347)
    val CottonCandyBlue = Color(0xFF87CEEB)
    val LollipopRed = Color(0xFFFF6B6B)
    val VanillaCream = Color(0xFFFFF5E1)
    val ChocolateBrown = Color(0xFF7B5B4F)
    val LicoriceGray = Color(0xFF4A4A4A)

    // Light theme color scheme
    val LightCandyColorScheme = lightColorScheme(
        primary = CandyPink,
        onPrimary = Color.White,
        primaryContainer = BubbleGumPink,
        onPrimaryContainer = LicoriceGray,

        secondary = MintGreen,
        onSecondary = LicoriceGray,
        secondaryContainer = CottonCandyBlue,
        onSecondaryContainer = LicoriceGray,

        tertiary = SoftPurple,
        onTertiary = Color.White,
        tertiaryContainer = CreamsicleOrange,
        onTertiaryContainer = LicoriceGray,

        background = VanillaCream,
        onBackground = LicoriceGray,
        surface = Color.White,
        onSurface = LicoriceGray,

        error = LollipopRed,
        onError = Color.White,
        errorContainer = Color(0xFFFFDAD6),
        onErrorContainer = Color(0xFF410002)
    )

    // Dark theme color scheme
    val DarkCandyColorScheme = darkColorScheme(
        primary = BubbleGumPink,
        onPrimary = Color.Black,
        primaryContainer = CandyPink,
        onPrimaryContainer = Color.White,

        secondary = CottonCandyBlue,
        onSecondary = Color.Black,
        secondaryContainer = MintGreen,
        onSecondaryContainer = Color.White,

        tertiary = CreamsicleOrange,
        onTertiary = Color.Black,
        tertiaryContainer = SoftPurple,
        onTertiaryContainer = Color.White,

        background = ChocolateBrown,
        onBackground = VanillaCream,
        surface = LicoriceGray,
        onSurface = VanillaCream,

        error = LollipopRed,
        onError = Color.Black,
        errorContainer = Color(0xFF93000A),
        onErrorContainer = Color(0xFFFFDAD6)
    )
}