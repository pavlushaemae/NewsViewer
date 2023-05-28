package com.itis.newsviewer.presentation.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itis.newsviewer.R
import com.itis.newsviewer.presentation.ui.custom.NewsViewerCorners
import com.itis.newsviewer.presentation.ui.custom.NewsViewerFontFamily
import com.itis.newsviewer.presentation.ui.custom.NewsViewerStyle
import com.itis.newsviewer.presentation.ui.custom.NewsViewerTheme
import com.itis.newsviewer.presentation.ui.custom.blueDarkPalette
import com.itis.newsviewer.presentation.ui.custom.blueLightPalette
import com.itis.newsviewer.presentation.ui.custom.orangeDarkPalette
import com.itis.newsviewer.presentation.ui.custom.orangeLightPalette
import com.itis.newsviewer.presentation.ui.custom.purpleDarkPalette
import com.itis.newsviewer.presentation.ui.custom.purpleLightPalette
import com.itis.newsviewer.utils.OnClick

@Composable
fun SettingsScreen() {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = NewsViewerTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = NewsViewerTheme.colors.primaryBackground,
                elevation = 8.dp
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = NewsViewerTheme.shapes.padding),
                    text = stringResource(id = R.string.screen_settings),
                    color = NewsViewerTheme.colors.primaryText,
                    style = NewsViewerTheme.typography.toolbar
                )
            }

            Row(
                modifier = Modifier.padding(NewsViewerTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Dark Theme",
                    color = NewsViewerTheme.colors.primaryText,
                    style = NewsViewerTheme.typography.body
                )
                Checkbox(
                    checked = currentSettings.isDarkMode, onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = NewsViewerTheme.colors.tintColor,
                        uncheckedColor = NewsViewerTheme.colors.secondaryText
                    )
                )
            }

            Divider(
                modifier = Modifier.padding(horizontal = NewsViewerTheme.shapes.padding),
                thickness = 0.5.dp,
                color = NewsViewerTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = NewsViewerTheme.colors.secondaryBackground,
                shape = NewsViewerTheme.shapes.cornerStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Shape type", color = NewsViewerTheme.colors.secondaryText)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = NewsViewerTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Round",
                                    color = NewsViewerTheme.colors.primaryText,
                                    style = NewsViewerTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.cornerStyle == NewsViewerCorners.Rounded,
                                    onCheckedChange = {
                                        settingsEventBus.updateCornerStyle(NewsViewerCorners.Rounded)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = NewsViewerTheme.colors.tintColor,
                                        uncheckedColor = NewsViewerTheme.colors.secondaryText
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = NewsViewerTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Flat",
                                    color = NewsViewerTheme.colors.primaryText,
                                    style = NewsViewerTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.cornerStyle == NewsViewerCorners.Flat,
                                    onCheckedChange = {
                                        settingsEventBus.updateCornerStyle(NewsViewerCorners.Flat)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = NewsViewerTheme.colors.tintColor,
                                        uncheckedColor = NewsViewerTheme.colors.secondaryText
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Divider(
                modifier = Modifier.padding(horizontal = NewsViewerTheme.shapes.padding),
                thickness = 0.5.dp,
                color = NewsViewerTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = NewsViewerTheme.colors.secondaryBackground,
                shape = NewsViewerTheme.shapes.cornerStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tint color", color = NewsViewerTheme.colors.secondaryText)

                    Row(
                        modifier = Modifier
                            .padding(NewsViewerTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ColorCard(color = if (currentSettings.isDarkMode) purpleDarkPalette.tintColor else purpleLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(NewsViewerStyle.Purple)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode) orangeDarkPalette.tintColor else orangeLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(NewsViewerStyle.Orange)
                            })
                        ColorCard(
                            color = if (currentSettings.isDarkMode) blueDarkPalette.tintColor else blueLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(NewsViewerStyle.Blue)
                            })
                    }
                }
            }

            Divider(
                modifier = Modifier
                    .padding(horizontal = NewsViewerTheme.shapes.padding),
                thickness = 0.5.dp,
                color = NewsViewerTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = NewsViewerTheme.colors.secondaryBackground,
                shape = NewsViewerTheme.shapes.cornerStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Font Family",
                        color = NewsViewerTheme.colors.secondaryText,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        FontCard(
                            modifier = Modifier.weight(1f),
                            fontFamily = NewsViewerFontFamily.Default,
                            checked = currentSettings.fontFamily == NewsViewerFontFamily.Default,
                            onClick = { settingsEventBus.updateFontFamily(NewsViewerFontFamily.Default) },
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        FontCard(
                            modifier = Modifier.weight(1f),
                            fontFamily = NewsViewerFontFamily.Cursive,
                            checked = currentSettings.fontFamily == NewsViewerFontFamily.Cursive,
                            onClick = { settingsEventBus.updateFontFamily(NewsViewerFontFamily.Cursive) },
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        FontCard(
                            modifier = Modifier.weight(1f),
                            fontFamily = NewsViewerFontFamily.Monospace,
                            checked = currentSettings.fontFamily == NewsViewerFontFamily.Monospace,
                            onClick = { settingsEventBus.updateFontFamily(NewsViewerFontFamily.Monospace) },
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColorCard(color: Color, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .size(56.dp, 56.dp),
        backgroundColor = color,
        shape = NewsViewerTheme.shapes.cornerStyle,
        elevation = 5.dp,
    ) {}
}

@Composable
private fun FontCard(
    modifier: Modifier,
    fontFamily: NewsViewerFontFamily,
    onClick: OnClick,
    checked: Boolean,
) {
    Card(
        modifier = modifier,
        backgroundColor = NewsViewerTheme.colors.primaryBackground
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                text = fontFamily.toString(),
                color = NewsViewerTheme.colors.primaryText,
                style = NewsViewerTheme.typography.body
            )
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    onClick.invoke()
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = NewsViewerTheme.colors.tintColor,
                    uncheckedColor = NewsViewerTheme.colors.secondaryText
                )
            )
        }
    }
}
