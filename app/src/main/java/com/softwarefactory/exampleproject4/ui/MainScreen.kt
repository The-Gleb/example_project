package com.softwarefactory.exampleproject4.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.softwarefactory.exampleproject4.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val TimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

internal fun formatTime(time: LocalTime): String = time.format(TimeFormatter)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var currentTime by remember { mutableStateOf(LocalTime.now()) }
    val formatted = formatTime(currentTime)
    val timeContentDescription = stringResource(R.string.cd_current_time, formatted)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = formatted,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.semantics {
                    contentDescription = timeContentDescription
                },
            )
            Text(
                text = stringResource(R.string.caption_tap_to_refresh),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Button(
                onClick = { currentTime = LocalTime.now() },
                contentPadding = PaddingValues(
                    horizontal = 24.dp,
                    vertical = 12.dp,
                ),
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = null,
                )
                Text(
                    text = stringResource(R.string.action_refresh),
                    modifier = Modifier.padding(start = 8.dp),
                )
            }
        }
    }
}
