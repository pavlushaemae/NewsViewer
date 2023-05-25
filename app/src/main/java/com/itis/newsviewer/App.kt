package com.itis.newsviewer

import android.app.Application
import com.itis.newsviewer.di.appModule
import com.itis.newsviewer.di.featureModule
import com.itis.newsviewer.di.networkModule
import com.itis.newsviewer.di.newsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
                newsModule,
                featureModule
            )
        }
    }
}
