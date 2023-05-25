package com.itis.newsviewer.presentation.screen.list

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule = module {
    viewModel { ListViewModel(get()) }
}
