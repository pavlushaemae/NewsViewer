package com.itis.newsviewer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.itis.newsviewer.presentation.screen.settings.LocalSettingsEventBus
import com.itis.newsviewer.presentation.screen.settings.SettingsEventBus
import com.itis.newsviewer.presentation.ui.custom.NewsViewerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val settingsEventBus = remember { SettingsEventBus() }
            val currentSettings = settingsEventBus.currentSettings.collectAsState().value

            NewsViewerTheme(
                style = currentSettings.style,
                darkTheme = currentSettings.isDarkMode,
                corners = currentSettings.cornerStyle,
                textSize = currentSettings.textSize,
                paddingSize = currentSettings.paddingSize,
                fontFamily = currentSettings.fontFamily
            ) {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {
                    NewsNavHost()
                }
            }
        }
    }
}


