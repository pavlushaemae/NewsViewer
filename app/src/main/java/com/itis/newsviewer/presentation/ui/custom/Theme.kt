package com.itis.newsviewer.presentation.ui.custom

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Composable
fun NewsViewerTheme(
    style: NewsViewerStyle = NewsViewerStyle.Purple,
    textSize: NewsViewerSize = NewsViewerSize.Medium,
    paddingSize: NewsViewerSize = NewsViewerSize.Medium,
    corners: NewsViewerCorners = NewsViewerCorners.Rounded,
    fontFamily: NewsViewerFontFamily = NewsViewerFontFamily.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = when {
        darkTheme -> {
            when (style) {
                NewsViewerStyle.Purple -> purpleDarkPalette
                NewsViewerStyle.Blue -> blueDarkPalette
                NewsViewerStyle.Orange -> orangeDarkPalette
                NewsViewerStyle.Red -> redDarkPalette
                NewsViewerStyle.Green -> greenDarkPalette
            }
        }

        else -> {
            when (style) {
                NewsViewerStyle.Purple -> purpleLightPalette
                NewsViewerStyle.Blue -> blueLightPalette
                NewsViewerStyle.Orange -> orangeLightPalette
                NewsViewerStyle.Red -> redLightPalette
                NewsViewerStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = NewsViewerTypography(
        heading = TextStyle(
            fontFamily = when (fontFamily) {
                NewsViewerFontFamily.Default -> FontFamily.Default
                NewsViewerFontFamily.Cursive -> FontFamily.Cursive
                NewsViewerFontFamily.Monospace -> FontFamily.Monospace
            },
            fontSize = when (textSize) {
                NewsViewerSize.Small -> 24.sp
                NewsViewerSize.Medium -> 28.sp
                NewsViewerSize.Large -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontFamily = when (fontFamily) {
                NewsViewerFontFamily.Default -> FontFamily.Default
                NewsViewerFontFamily.Cursive -> FontFamily.Cursive
                NewsViewerFontFamily.Monospace -> FontFamily.Monospace
            },
            fontSize = when (textSize) {
                NewsViewerSize.Small -> 14.sp
                NewsViewerSize.Medium -> 16.sp
                NewsViewerSize.Large -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = when (textSize) {
                NewsViewerSize.Small -> 14.sp
                NewsViewerSize.Medium -> 16.sp
                NewsViewerSize.Large -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontFamily = when (fontFamily) {
                NewsViewerFontFamily.Default -> FontFamily.Default
                NewsViewerFontFamily.Cursive -> FontFamily.Cursive
                NewsViewerFontFamily.Monospace -> FontFamily.Monospace
            },
            fontSize = when (textSize) {
                NewsViewerSize.Small -> 10.sp
                NewsViewerSize.Medium -> 12.sp
                NewsViewerSize.Large -> 14.sp
            }
        ),
        bodySmall = TextStyle(
            fontSize = when (textSize) {
                NewsViewerSize.Small -> 14.sp
                NewsViewerSize.Medium -> 16.sp
                NewsViewerSize.Large -> 18.sp
            },
            fontWeight = FontWeight.Light
        ),
    )

    val shapes = NewsViewerShapes(
        padding = when (paddingSize) {
            NewsViewerSize.Small -> 12.dp
            NewsViewerSize.Medium -> 16.dp
            NewsViewerSize.Large -> 20.dp
        },
        cornerStyle = when (corners) {
            NewsViewerCorners.Flat -> RoundedCornerShape(0.dp)
            NewsViewerCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primaryBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalNewsViewerColors provides colors,
        LocalNewsViewerTypography provides typography,
        LocalNewsViewerShapes provides shapes,
        content = content
    )
}
