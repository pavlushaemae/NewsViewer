package com.itis.newsviewer.data.news.datasource.remote.interceptor

import com.itis.newsviewer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter("api_token", BuildConfig.API_KEY)
            .build()
        return chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }
}
