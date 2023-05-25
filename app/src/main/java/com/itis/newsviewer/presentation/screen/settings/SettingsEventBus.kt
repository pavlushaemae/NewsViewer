package com.itis.newsviewer.presentation.screen.settings

import androidx.compose.runtime.staticCompositionLocalOf
import com.itis.newsviewer.presentation.ui.custom.NewsViewerCorners
import com.itis.newsviewer.presentation.ui.custom.NewsViewerFontFamily
import com.itis.newsviewer.presentation.ui.custom.NewsViewerSize
import com.itis.newsviewer.presentation.ui.custom.NewsViewerStyle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CurrentSettings(
    val isDarkMode: Boolean,
    val textSize: NewsViewerSize,
    val paddingSize: NewsViewerSize,
    val cornerStyle: NewsViewerCorners,
    val style: NewsViewerStyle,
    val fontFamily: NewsViewerFontFamily,
)

class SettingsEventBus {

    private val _currentSettings: MutableStateFlow<CurrentSettings> = MutableStateFlow(
        CurrentSettings(
            isDarkMode = true,
            cornerStyle = NewsViewerCorners.Rounded,
            style = NewsViewerStyle.Orange,
            textSize = NewsViewerSize.Medium,
            paddingSize = NewsViewerSize.Medium,
            fontFamily = NewsViewerFontFamily.Default
        )
    )
    val currentSettings: StateFlow<CurrentSettings> = _currentSettings

    fun updateDarkMode(isDarkMode: Boolean) {
        _currentSettings.value = _currentSettings.value.copy(isDarkMode = isDarkMode)
    }

    fun updateCornerStyle(corners: NewsViewerCorners) {
        _currentSettings.value = _currentSettings.value.copy(cornerStyle = corners)
    }

    fun updateStyle(style: NewsViewerStyle) {
        _currentSettings.value = _currentSettings.value.copy(style = style)
    }

    fun updateFontFamily(fontFamily: NewsViewerFontFamily) {
        _currentSettings.value = _currentSettings.value.copy(fontFamily = fontFamily)
    }
}

val LocalSettingsEventBus = staticCompositionLocalOf {
    SettingsEventBus()
}
