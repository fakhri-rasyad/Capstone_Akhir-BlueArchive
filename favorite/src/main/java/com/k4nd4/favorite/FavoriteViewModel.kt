package com.k4nd4.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.domain.usecase.BlueArchiveUseCase

class FavoriteViewModel(private val blueArchiveUseCase : BlueArchiveUseCase) : ViewModel() {
    val students : LiveData<List<Student>> = blueArchiveUseCase.getFavoriteStudent().asLiveData()
}