package com.k4nd4.core.data.source.local

import com.k4nd4.core.data.source.local.entity.StudentEntity
import com.k4nd4.core.data.source.local.room.StudentDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val studentDao: StudentDao) {

    fun getAllStudents() : Flow<List<StudentEntity>> = studentDao.getStudents()

    fun getFavoriteStudent() : Flow<List<StudentEntity>> = studentDao.getFavoriteStudents()

    suspend fun insertStudent(studentEntity: List<StudentEntity>) = studentDao.insertStudent(studentEntity)

    suspend fun updateStudent(studentEntity: StudentEntity) = studentDao.updateStudent(studentEntity)

}