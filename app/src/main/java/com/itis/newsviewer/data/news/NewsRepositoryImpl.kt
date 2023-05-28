package com.itis.newsviewer.data.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itis.newsviewer.data.news.datasource.remote.NewsApi
import com.itis.newsviewer.data.news.datasource.remote.NewsPagingSource
import com.itis.newsviewer.data.news.mapper.toNewsInfo
import com.itis.newsviewer.domain.news.NewsRepository
import com.itis.newsviewer.domain.news.model.NewsInfo
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val newsPagingSource: NewsPagingSource,
) : NewsRepository {
    override suspend fun getNewsById(uuid: String): NewsInfo {
        return api.getNewsById(uuid).toNewsInfo()
    }

    override fun getNewsList(): Flow<PagingData<NewsInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            pagingSourceFactory = {
                newsPagingSource
            }
        ).flow
    }
}
