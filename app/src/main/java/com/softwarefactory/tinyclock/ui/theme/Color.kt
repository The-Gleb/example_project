package com.softwarefactory.tinyclock.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Fallback Material 3 scheme derived from Material green 600 (#2E7D32) seed.
// Used on devices without dynamic color support (API < 31 or when disabled).

private val GreenPrimaryLight = Color(0xFF006E1C)
private val GreenOnPrimaryLight = Color(0xFFFFFFFF)
private val GreenPrimaryContainerLight = Color(0xFF79FF80)
private val GreenOnPrimaryContainerLight = Color(0xFF002204)
private val GreenSecondaryLight = Color(0xFF53634D)
private val GreenOnSecondaryLight = Color(0xFFFFFFFF)
private val GreenBackgroundLight = Color(0xFFFCFDF6)
private val GreenOnBackgroundLight = Color(0xFF1A1C18)

private val GreenPrimaryDark = Color(0xFF7DDC85)
private val GreenOnPrimaryDark = Color(0xFF003908)
private val GreenPrimaryContainerDark = Color(0xFF00531A)
private val GreenOnPrimaryContainerDark = Color(0xFF95F89D)
private val GreenSecondaryDark = Color(0xFFBBCBB1)
private val GreenOnSecondaryDark = Color(0xFF263420)
private val GreenBackgroundDark = Color(0xFF1A1C18)
private val GreenOnBackgroundDark = Color(0xFFE2E3DC)

val GreenLightColorScheme = lightColorScheme(
    primary = GreenPrimaryLight,
    onPrimary = GreenOnPrimaryLight,
    primaryContainer = GreenPrimaryContainerLight,
    onPrimaryContainer = GreenOnPrimaryContainerLight,
    secondary = GreenSecondaryLight,
    onSecondary = GreenOnSecondaryLight,
    background = GreenBackgroundLight,
    onBackground = GreenOnBackgroundLight,
)

val GreenDarkColorScheme = darkColorScheme(
    primary = GreenPrimaryDark,
    onPrimary = GreenOnPrimaryDark,
    primaryContainer = GreenPrimaryContainerDark,
    onPrimaryContainer = GreenOnPrimaryContainerDark,
    secondary = GreenSecondaryDark,
    onSecondary = GreenOnSecondaryDark,
    background = GreenBackgroundDark,
    onBackground = GreenOnBackgroundDark,
)
