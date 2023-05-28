package com.itis.newsviewer.presentation.screen.stub

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.itis.newsviewer.R
import com.itis.newsviewer.presentation.ui.custom.NewsViewerTheme

@Composable
fun StubScreen() {
    MainContent()
}

@Composable
fun MainContent() {
    Surface(
        color = NewsViewerTheme.colors.primaryBackground,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = NewsViewerTheme.colors.secondaryBackground,
                elevation = 8.dp
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = NewsViewerTheme.shapes.padding),
                    text = stringResource(id = R.string.screen_stub),
                    color = NewsViewerTheme.colors.primaryText,
                    style = NewsViewerTheme.typography.toolbar
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = "This is Stub Screen =)",
                    style = NewsViewerTheme.typography.body,
                    color = NewsViewerTheme.colors.controlColor,
                )
            }
        }
    }
}
