package com.k4nd4.capstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.k4nd4.core.domain.usecase.BlueArchiveUseCase

class HomeViewModel(blueArchiveUseCase: BlueArchiveUseCase) : ViewModel() {
    val students = blueArchiveUseCase.getAllStudent().asLiveData()
}