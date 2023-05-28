package com.itis.newsviewer.di

import com.itis.newsviewer.data.news.NewsRepositoryImpl
import com.itis.newsviewer.data.news.datasource.remote.NewsApi
import com.itis.newsviewer.data.news.datasource.remote.NewsPagingSource
import com.itis.newsviewer.domain.news.GetNewsByIdUseCase
import com.itis.newsviewer.domain.news.GetNewsUseCase
import com.itis.newsviewer.domain.news.NewsRepository
import org.koin.dsl.module

val newsModule = module {

    single { provideNewsRepository(get(), get()) }
    single { providePagingSource(get()) }
    factory { provideGetNewsUseCase(get()) }
    factory { provideGetNewsByIdUseCase(get()) }
}

private fun provideNewsRepository(
    newsApi: NewsApi,
    newsPagingSource: NewsPagingSource,
): NewsRepository = NewsRepositoryImpl(newsApi, newsPagingSource)

private fun providePagingSource(
    newsApi: NewsApi,
): NewsPagingSource = NewsPagingSource(newsApi)

private fun provideGetNewsUseCase(
    newsRepository: NewsRepository,
): GetNewsUseCase = GetNewsUseCase(newsRepository)

private fun provideGetNewsByIdUseCase(
    newsRepository: NewsRepository,
): GetNewsByIdUseCase = GetNewsByIdUseCase(newsRepository)
