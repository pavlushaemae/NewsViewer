package com.itis.newsviewer.presentation.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.itis.newsviewer.R
import com.itis.newsviewer.domain.news.model.NewsInfo
import com.itis.newsviewer.presentation.Screen
import com.itis.newsviewer.presentation.ui.custom.NewsViewerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event,
    )

    ListScreenActions(
        navController = navController,
        viewAction = action
    )
}

@Composable
fun MainContent(
    viewState: ListViewState,
    eventHandler: (ListEvent) -> Unit,
) {
    Column {
        TopAppBar(
            backgroundColor = NewsViewerTheme.colors.primaryBackground,
            elevation = 8.dp
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = NewsViewerTheme.shapes.padding),
                text = stringResource(id = R.string.screen_list),
                color = NewsViewerTheme.colors.primaryText,
                style = NewsViewerTheme.typography.toolbar
            )
        }
        LazyColumnSample(viewState, eventHandler)
    }
}

@Composable
fun LazyColumnSample(
    viewState: ListViewState,
    eventHandler: (ListEvent) -> Unit,
) {
    val news = viewState.news.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(NewsViewerTheme.colors.primaryBackground),
    ) {
        items(
            count = news.itemCount,
            key = news.itemKey(key = { it.uuid }),
        ) { index ->
            val item = news[index]
            item?.let {
                MyListItem(newsInfo = it) { item ->
                    eventHandler.invoke(ListEvent.OnNewsItemClick(item))
                }
            }
        }
        when (news.loadState.refresh) {
            is LoadState.Error -> {
                item {
                    ErrorMessage()
                }
            }

            is LoadState.Loading -> {
                item {
                    ProgressBar(
                        modifier = Modifier
                            .fillParentMaxSize()
                    )
                }
            }

            else -> Unit
        }
        when (news.loadState.append) {
            is LoadState.Error -> {
                item {
                    ErrorMessage()
                }
            }

            is LoadState.Loading -> {
                item {
                    ProgressBar(
                        modifier = Modifier
                            .fillParentMaxSize()
                    )
                }
            }

            else -> Unit
        }
    }
}

@Composable
private fun ProgressBar(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(color = NewsViewerTheme.colors.tintColor)
    }
}

@Composable
private fun ErrorMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.error),
            style = NewsViewerTheme.typography.body,
            color = NewsViewerTheme.colors.errorColor
        )
    }
}

@Composable
private fun MyListItem(
    newsInfo: NewsInfo,
    onClick: (NewsInfo) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(newsInfo)
            }
            .padding(8.dp)
            .background(
                color = NewsViewerTheme.colors.secondaryBackground,
                shape = NewsViewerTheme.shapes.cornerStyle
            )
            .padding(16.dp)
    ) {
        Text(
            text = newsInfo.title,
            style = NewsViewerTheme.typography.body,
            color = NewsViewerTheme.colors.primaryText
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            shape = NewsViewerTheme.shapes.cornerStyle
        ) {
            AsyncImage(
                model = newsInfo.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )
        }
    }
}

@Composable
private fun ListScreenActions(
    navController: NavController,
    viewAction: ListAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            is ListAction.Navigate -> {
                navController.navigate(Screen.Detail.route + "/${viewAction.id}")
            }
        }
    }
}
