package com.softwarefactory.tinyclock.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val TealPrimaryLight = Color(0xFF006A60)
private val TealOnPrimaryLight = Color(0xFFFFFFFF)
private val TealPrimaryContainerLight = Color(0xFF74F8E5)
private val TealOnPrimaryContainerLight = Color(0xFF00201C)
private val TealSecondaryLight = Color(0xFF4A635F)
private val TealOnSecondaryLight = Color(0xFFFFFFFF)
private val TealBackgroundLight = Color(0xFFFAFDFB)
private val TealOnBackgroundLight = Color(0xFF191C1B)

private val TealPrimaryDark = Color(0xFF53DBC9)
private val TealOnPrimaryDark = Color(0xFF003731)
private val TealPrimaryContainerDark = Color(0xFF005048)
private val TealOnPrimaryContainerDark = Color(0xFF74F8E5)
private val TealSecondaryDark = Color(0xFFB1CCC6)
private val TealOnSecondaryDark = Color(0xFF1C3531)
private val TealBackgroundDark = Color(0xFF191C1B)
private val TealOnBackgroundDark = Color(0xFFE0E3E1)

val TealLightColorScheme = lightColorScheme(
    primary = TealPrimaryLight,
    onPrimary = TealOnPrimaryLight,
    primaryContainer = TealPrimaryContainerLight,
    onPrimaryContainer = TealOnPrimaryContainerLight,
    secondary = TealSecondaryLight,
    onSecondary = TealOnSecondaryLight,
    background = TealBackgroundLight,
    onBackground = TealOnBackgroundLight,
)

val TealDarkColorScheme = darkColorScheme(
    primary = TealPrimaryDark,
    onPrimary = TealOnPrimaryDark,
    primaryContainer = TealPrimaryContainerDark,
    onPrimaryContainer = TealOnPrimaryContainerDark,
    secondary = TealSecondaryDark,
    onSecondary = TealOnSecondaryDark,
    background = TealBackgroundDark,
    onBackground = TealOnBackgroundDark,
)
