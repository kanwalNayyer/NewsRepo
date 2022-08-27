package com.hisham.news.di

import com.hisham.news.ui.ui.home.NavigationViewModel
import com.hisham.news.ui.ui.home.learn.feed.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { NavigationViewModel() }
    viewModel { NewsViewModel(get()) }
}