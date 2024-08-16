package com.k4nd4.favorite

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    viewModel { FavoriteViewModel(get()) }
}