package com.itis.newsviewer.di

import com.itis.newsviewer.data.news.NewsRepositoryImpl
import com.itis.newsviewer.domain.news.NewsRepository
import dagger.Binds
import dagger.Module

@Module
interface NewsModule {

    @Binds
    fun provideNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
