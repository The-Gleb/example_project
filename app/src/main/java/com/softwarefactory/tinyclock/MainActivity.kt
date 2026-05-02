package com.softwarefactory.tinyclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.softwarefactory.tinyclock.ui.ClockScreen
import com.softwarefactory.tinyclock.ui.theme.TinyClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TinyClockTheme {
                ClockScreen()
            }
        }
    }
}
