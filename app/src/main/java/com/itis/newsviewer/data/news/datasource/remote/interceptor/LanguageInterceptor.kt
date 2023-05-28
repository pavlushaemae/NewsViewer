package com.itis.newsviewer.data.news.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newURL = original.url.newBuilder()
            .addQueryParameter("language", "ru")
            .build()
        return chain.proceed(
            original.newBuilder()
                .url(newURL)
                .build()
        )
    }
}
