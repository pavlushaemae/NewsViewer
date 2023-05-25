package com.itis.newsviewer.di

import com.itis.newsviewer.BuildConfig
import com.itis.newsviewer.data.news.datasource.remote.NewsApi
import com.itis.newsviewer.data.news.datasource.remote.interceptor.ApiKeyInterceptor
import com.itis.newsviewer.data.news.datasource.remote.interceptor.LanguageInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InterceptApiKey
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InterceptLogger
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InterceptLanguage

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @InterceptLogger
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
    @Provides
    @InterceptApiKey
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()
    @Provides
    @InterceptLanguage
    fun provideLanguageInterceptor(): Interceptor = LanguageInterceptor()
    @Provides
    fun provideHttpClient(
        @InterceptApiKey apiKeyInterceptor: Interceptor,
        @InterceptLogger loggingInterceptor: Interceptor,
        @InterceptLanguage languageInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(languageInterceptor)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
    @Provides
    fun provideBaseUrl(): String = BuildConfig.API_ENDPOINT
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(gsonFactory)
        .build()
    @Provides
    fun provideNewsApi(
        retrofit: Retrofit
    ): NewsApi = retrofit.create(NewsApi::class.java)
}
