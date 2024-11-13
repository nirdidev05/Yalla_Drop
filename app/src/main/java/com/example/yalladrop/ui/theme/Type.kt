package com.example.yalladrop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.yalladrop.R
val leagueSpartan = FontFamily(
    Font(R.font.league_spartan_regular, FontWeight.Normal),
    Font(R.font.league_spartan_bold,FontWeight.Bold),
    Font(R.font.league_spartan_semibold, FontWeight.SemiBold),
    Font(R.font.league_spartan_extrabold, FontWeight.ExtraBold),
    Font(R.font.league_spartan_light, FontWeight.Light),
    Font(R.font.league_spartan_extralight, FontWeight.ExtraLight),
    Font(R.font.league_spartan_medium, FontWeight.Medium),
    Font(R.font.league_spartan_thin, FontWeight.Thin),
    Font(R.font.league_spartan_black, FontWeight.Black),

    )
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),
    titleLarge = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),
    titleMedium = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),

    labelMedium = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    labelSmall = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp,
        color = Color.Black
    ),
    displayLarge = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 30.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),
    displaySmall = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),
    displayMedium = TextStyle(
        fontFamily = leagueSpartan,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.5.sp,
        color = Color.Black
    ),

)

