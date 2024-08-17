package com.k4nd4.core.domain.usecase

import com.k4nd4.core.data.Resource
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.domain.repository.IBlueArchiveRepository
import kotlinx.coroutines.flow.Flow

class BlueArchiveInteractor(private val repository: IBlueArchiveRepository) : BlueArchiveUseCase {
    override fun getStudentFromAPI(): Flow<Resource<List<Student>>> = repository.getStudentFromAPI()
    override fun getAllStudent(): Flow<List<Student>> = repository.getAllStudent()
    override fun getFavoriteStudent(): Flow<List<Student>> = repository.getFavoriteStudent()
    override fun insertStudent(students: List<Student>) = repository.insertStudent(students)
    override fun updateFavoriteStudent(student: Student) = repository.updateFavoriteStudent(student)
}