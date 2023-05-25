package com.itis.newsviewer.di

import com.itis.newsviewer.presentation.screen.detail.detailModule
import com.itis.newsviewer.presentation.screen.list.listModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        listModule,
        detailModule
    )
}
