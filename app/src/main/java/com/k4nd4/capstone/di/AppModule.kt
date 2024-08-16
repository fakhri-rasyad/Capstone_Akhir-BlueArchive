package com.k4nd4.capstone.di

import com.k4nd4.capstone.utils.AppIntentProvider
import com.k4nd4.capstone.viewmodel.DetailViewModel
import com.k4nd4.capstone.viewmodel.HomeViewModel
import com.k4nd4.capstone.viewmodel.MainViewModel
import com.k4nd4.core.domain.usecase.BlueArchiveInteractor
import com.k4nd4.core.domain.usecase.BlueArchiveUseCase
import com.k4nd4.core.utils.IntentProvider
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BlueArchiveUseCase> { BlueArchiveInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

val appInjectModule = module {
    factory<IntentProvider> { AppIntentProvider() }
}