package com.itis.newsviewer.ui.screen.list

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
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.itis.newsviewer.domain.news.model.NewsInfo
import com.itis.newsviewer.ui.theme.NewsViewerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event,
    )
}

@Composable
fun MainContent(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {
    LazyColumnSample(viewState, eventHandler)
}

@Composable
fun LazyColumnSample(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {
    val news = viewState.news.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        items(
            count = news.itemCount,
            key = news.itemKey(key = { it.uuid }),
        ) { index ->
            val item = news[index]
            item?.let {
                MyListItem(newsInfo = it) { item ->
                    eventHandler.invoke(MainEvent.onNewsItemClick(item))
                }
            }
            Divider()
        }
        when (val state = news.loadState.refresh) {
            is LoadState.Error -> {

            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
        when (val state = news.loadState.append) { // Pagination
            is LoadState.Error -> {

            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
}

@Composable
fun MyListItem(
    newsInfo: NewsInfo,
    onClick: (NewsInfo) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clickable {
                onClick.invoke(newsInfo)
            }
            .padding(16.dp)
    ) {
        Text(
            text = newsInfo.title,
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp
        )
        Text(
            text = newsInfo.description,
            fontFamily = FontFamily.Cursive
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
//            Image(
//                painterResource(R.drawable.image),
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Blue)
//            )

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

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    NewsViewerTheme {
        MyListItem(
            NewsInfo(
                uuid = "1",
                categories = listOf("vehicle"),
                description = "В Казани спасили кота Васю,который застрял на дереве...",
                imageUrl = "https://static.1tv.ru/uploads/video/material/splash/2022/08/16/739756/big/739756_big_41eceeb1b1.jpg",
                keywords = "",
                language = "",
                publishedAt = "",
                snippet = "",
                source = "",
                title = "Выпуск новостей в 15:00",
                url = "https",
            ), {})
    }
}
