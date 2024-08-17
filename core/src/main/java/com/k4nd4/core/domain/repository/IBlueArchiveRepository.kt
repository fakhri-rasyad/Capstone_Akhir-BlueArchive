package com.k4nd4.core.domain.repository

import com.k4nd4.core.data.Resource
import com.k4nd4.core.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface IBlueArchiveRepository {
    fun getStudentFromAPI(): Flow<Resource<List<Student>>>
    fun getAllStudent() : Flow<List<Student>>
    fun getFavoriteStudent() : Flow<List<Student>>
    fun insertStudent(students : List<Student>)
    fun updateFavoriteStudent(student: Student)
}