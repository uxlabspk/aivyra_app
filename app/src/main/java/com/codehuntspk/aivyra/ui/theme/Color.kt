package com.codehuntspk.aivyra.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Brand Colors
val Cyan400 = Color(0xFF22D3EE)
val Cyan500 = Color(0xFF06B6D4)
val Purple500 = Color(0xFFA855F7)
val Purple600 = Color(0xFF9333EA)
val Purple900 = Color(0xFF581C87)
val Pink400 = Color(0xFFF472B6)
val Pink500 = Color(0xFFEC4899)

// Background Colors
val Slate900 = Color(0xFF0F172A)
val Slate800 = Color(0xFF1E293B)

// Surface Colors with Transparency
val SurfaceWhite5 = Color(0x0DFFFFFF)  // White with 5% opacity
val SurfaceWhite10 = Color(0x1AFFFFFF) // White with 10% opacity
val SurfaceWhite20 = Color(0x33FFFFFF) // White with 20% opacity

// Border Colors
val BorderWhite10 = Color(0x1AFFFFFF)  // White with 10% opacity
val BorderWhite20 = Color(0x33FFFFFF)  // White with 20% opacity
val BorderCyan50 = Color(0x80EDF7FA)   // Cyan with 50% opacity

// Text Colors
val TextWhite = Color(0xFFFFFFFF)
val TextWhite80 = Color(0xCCFFFFFF)    // White with 80% opacity
val TextWhite70 = Color(0xB3FFFFFF)    // White with 70% opacity
val TextWhite60 = Color(0x99FFFFFF)    // White with 60% opacity
val TextWhite50 = Color(0x80FFFFFF)    // White with 50% opacity
val TextWhite40 = Color(0x66FFFFFF)    // White with 40% opacity

// Gradient Colors (use these for gradient definitions)
object GradientColors {
    val cyanToPurple = listOf(Cyan500, Purple600)
    val cyanToPurpleAlt = listOf(Cyan400, Purple600)
    val cyanViaPurpleToPink = listOf(Cyan400, Purple500, Pink500)
    val purpleToCyan = listOf(Purple600, Cyan500)
    val backgroundGradient = listOf(Slate900, Purple900, Slate900)

    // CTA Gradient with transparency
    val ctaGradient = listOf(
        Color(0x3306B6D4), // Cyan500 with 20% opacity
        Color(0x339333EA)  // Purple600 with 20% opacity
    )
}

// Status Colors
val Success = Color(0xFF10B981)
val Warning = Color(0xFFF59E0B)
val Error = Color(0xFFEF4444)
val Info = Cyan400