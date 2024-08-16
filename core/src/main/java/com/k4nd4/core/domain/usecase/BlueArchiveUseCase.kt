package com.k4nd4.core.domain.usecase

import com.k4nd4.core.data.Resource
import com.k4nd4.core.data.source.local.entity.StudentEntity
import com.k4nd4.core.data.source.remote.network.ApiResponse
import com.k4nd4.core.data.source.remote.response.StudentResponse
import com.k4nd4.core.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface BlueArchiveUseCase {
    fun getStudentFromAPI() : Flow<Resource<List<Student>>>
    fun getAllStudent() : Flow<List<Student>>
    fun getFavoriteStudent() : Flow<List<Student>>
    fun insertStudent(students : List<Student>)
    fun updateFavoriteStudent(student: Student)
}