package com.softwarefactory.exampleproject4.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val SeedPrimary = Color(0xFF006A6A)
private val SeedOnPrimary = Color(0xFFFFFFFF)
private val SeedPrimaryContainer = Color(0xFF6FF7F6)
private val SeedOnPrimaryContainer = Color(0xFF002020)
private val SeedSecondary = Color(0xFF4A6363)
private val SeedSurfaceVariant = Color(0xFFDAE5E4)
private val SeedOnSurfaceVariant = Color(0xFF3F4948)

private val DarkSeedPrimary = Color(0xFF4DDADA)
private val DarkSeedOnPrimary = Color(0xFF003737)
private val DarkSeedPrimaryContainer = Color(0xFF004F50)
private val DarkSeedOnPrimaryContainer = Color(0xFF6FF7F6)
private val DarkSeedSecondary = Color(0xFFB1CCCB)
private val DarkSeedSurfaceVariant = Color(0xFF3F4948)
private val DarkSeedOnSurfaceVariant = Color(0xFFBEC9C8)

val FallbackLightColors = lightColorScheme(
    primary = SeedPrimary,
    onPrimary = SeedOnPrimary,
    primaryContainer = SeedPrimaryContainer,
    onPrimaryContainer = SeedOnPrimaryContainer,
    secondary = SeedSecondary,
    surfaceVariant = SeedSurfaceVariant,
    onSurfaceVariant = SeedOnSurfaceVariant,
)

val FallbackDarkColors = darkColorScheme(
    primary = DarkSeedPrimary,
    onPrimary = DarkSeedOnPrimary,
    primaryContainer = DarkSeedPrimaryContainer,
    onPrimaryContainer = DarkSeedOnPrimaryContainer,
    secondary = DarkSeedSecondary,
    surfaceVariant = DarkSeedSurfaceVariant,
    onSurfaceVariant = DarkSeedOnSurfaceVariant,
)
