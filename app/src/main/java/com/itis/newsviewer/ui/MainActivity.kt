package com.itis.newsviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.itis.newsviewer.domain.news.model.NewsInfo
import com.itis.newsviewer.ui.theme.NewsViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event,
        viewModel
    )
}

@Composable
fun MainContent(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
    viewModel: MainViewModel,
) {
    NewsViewerTheme {
        LazyColumnSample(viewState, eventHandler, viewModel)
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Composable
//fun ListLayout() {
//    Column {
//        LazyColumnSample()
//    }
//}

@Composable
fun LazyColumnSample(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
    viewModel: MainViewModel
) {
    val news = viewModel.getNews().collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        items(news, key = { it.uuid }) {
            it?.let{
                MyListItem(newsInfo = it) {

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
    onClick: (NewsInfo) -> Unit
) {
    Column(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Text(text = newsInfo.title)
        Text(text = newsInfo.url)
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun GreetingPreview() {
//    NewsViewerTheme {
//        MainContent(viewState = MainViewState(
//            listOf(
//                NewsInfo(
//                    uuid = "1",
//                    categories = listOf("vehicle"),
//                    description = "news",
//                    imageUrl = "",
//                    keywords = "",
//                    language = "",
//                    publishedAt = "",
//                    snippet = "",
//                    source = "",
//                    title = "News",
//                    url = "https",
//                )
//            )
//        ), eventHandler = {})
//    }
//}
