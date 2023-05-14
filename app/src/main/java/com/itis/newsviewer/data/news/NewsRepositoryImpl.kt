package com.itis.newsviewer.data.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itis.newsviewer.data.news.datasource.remote.NewsApi
import com.itis.newsviewer.data.news.datasource.remote.NewsPagingSource
import com.itis.newsviewer.data.news.mapper.toNewsInfo
import com.itis.newsviewer.data.news.mapper.toNewsInfoList
import com.itis.newsviewer.domain.news.NewsRepository
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
): NewsRepository {
    override suspend fun getNewsByUuid(uuid: String): NewsInfo {
        return api.getNewsById(uuid).toNewsInfo()
    }

    override fun getNewsList(): Flow<PagingData<NewsInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = api)
            }
        ).flow
    }
}
