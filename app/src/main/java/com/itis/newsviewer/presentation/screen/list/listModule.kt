package com.itis.newsviewer.ui

import com.itis.newsviewer.presentation.screen.list.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(get()) }
}
