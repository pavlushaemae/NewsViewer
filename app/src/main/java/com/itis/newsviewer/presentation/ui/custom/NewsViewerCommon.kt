package com.itis.newsviewer.presentation.ui.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class NewsViewerColors(
    val primaryText: Color,
    val secondaryText: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color,
)

data class NewsViewerTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle,
    val bodySmall: TextStyle,
)

data class NewsViewerShapes(
    val padding: Dp,
    val cornerStyle: Shape,
)

data class NewsViewerImages(
    val id: Int,
    val contentDescription: String,
)

object NewsViewerTheme {
    val colors: NewsViewerColors
        @Composable
        get() = LocalNewsViewerColors.current

    val typography: NewsViewerTypography
        @Composable
        get() = LocalNewsViewerTypography.current

    val shapes: NewsViewerShapes
        @Composable
        get() = LocalNewsViewerShapes.current
}

enum class NewsViewerStyle {
    Purple, Orange, Blue, Red, Green
}

enum class NewsViewerSize {
    Small, Medium, Large
}

enum class NewsViewerCorners {
    Flat, Rounded
}

enum class NewsViewerFontFamily {
    Default, Cursive, Monospace
}

val LocalNewsViewerColors = staticCompositionLocalOf<NewsViewerColors> {
    error("No colors provided")
}

val LocalNewsViewerTypography = staticCompositionLocalOf<NewsViewerTypography> {
    error("No font provided")
}

val LocalNewsViewerShapes = staticCompositionLocalOf<NewsViewerShapes> {
    error("No shapes provided")
}
