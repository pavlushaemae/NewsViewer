package com.itis.newsviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.itis.newsviewer.ui.theme.NewsViewerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsViewerTheme {
                CompositionLocalProvider{
                    NewsNavHost()
                }
            }
        }
    }
}


