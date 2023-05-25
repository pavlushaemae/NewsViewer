package com.itis.newsviewer.presentation.screen.detail

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel { DetailViewModel(get()) }
}
