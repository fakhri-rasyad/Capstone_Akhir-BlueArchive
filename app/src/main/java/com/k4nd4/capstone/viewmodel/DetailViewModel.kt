package com.k4nd4.capstone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.domain.usecase.BlueArchiveUseCase

class DetailViewModel(private val blueArchiveUseCase: BlueArchiveUseCase) : ViewModel() {
    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun updateStudent(studentEntity: Student, newState: Boolean) {
        updateIsFavorite(newState)
        studentEntity.isFavorite = isFavorite.value ?: false
        blueArchiveUseCase.updateFavoriteStudent(studentEntity)
    }

    fun updateIsFavorite(newState: Boolean) {
        _isFavorite.value = newState
    }

}