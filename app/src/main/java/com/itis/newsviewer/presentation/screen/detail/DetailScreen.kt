package com.itis.newsviewer.presentation.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itis.newsviewer.R
import com.itis.newsviewer.domain.news.model.NewsInfo
import com.itis.newsviewer.presentation.ui.custom.NewsViewerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = koinViewModel(),
    id: String?,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    id?.let {
        viewModel.event(DetailEvent.Init(id))
    }
    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event,
    )
}

@Composable
fun MainContent(
    viewState: DetailViewState,
    eventHandler: (DetailEvent) -> Unit,
) {
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
                    text = stringResource(id = R.string.screen_detail),
                    color = NewsViewerTheme.colors.primaryText,
                    style = NewsViewerTheme.typography.toolbar
                )
            }
            viewState.news?.also {
                NewsDetails(newsInfo = it) {
                    eventHandler.invoke(DetailEvent.OnUrlClick)
                }
            } ?: ProgressBar()
        }
    }
}

@Composable
private fun NewsDetails(newsInfo: NewsInfo, onUrlClick: () -> Unit) {
    ImageCard(imageUrl = newsInfo.imageUrl)
    Spacer(modifier = Modifier.height(4.dp))
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        backgroundColor = NewsViewerTheme.colors.secondaryBackground,
        shape = NewsViewerTheme.shapes.cornerStyle,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = newsInfo.title,
                style = NewsViewerTheme.typography.body,
                color = NewsViewerTheme.colors.primaryText,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = newsInfo.description,
                style = NewsViewerTheme.typography.body,
                color = NewsViewerTheme.colors.secondaryText
            )
            Row {
                Text(
                    text = "Source: ",
                    color = NewsViewerTheme.colors.secondaryText,
                    textAlign = TextAlign.Start
                )
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsInfo.url))
                val context = LocalContext.current
                Text(
                    text = newsInfo.source,
                    color = NewsViewerTheme.colors.tintColor,
                    modifier = Modifier
                        .clickable {
                            onUrlClick.invoke()
                            context.startActivity(intent)
                        }
                        .alignByBaseline()
                )
            }
        }
    }
}

@Composable
private fun ImageCard(imageUrl: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp),
        shape = NewsViewerTheme.shapes.cornerStyle
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()

        )
    }
}

@Composable
private fun ProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(color = NewsViewerTheme.colors.tintColor)
    }
}
