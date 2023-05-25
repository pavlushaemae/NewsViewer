package com.itis.newsviewer.di

import android.app.Application
import android.content.Context
import org.koin.dsl.module

val appModule = module {
    single { provideContext(get()) }
}

fun provideContext(application: Application): Context = application.applicationContext
