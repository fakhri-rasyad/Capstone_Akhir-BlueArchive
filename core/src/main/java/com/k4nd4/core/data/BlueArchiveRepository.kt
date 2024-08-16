package com.k4nd4.core.data

import com.k4nd4.core.data.source.local.LocalDataSource
import com.k4nd4.core.data.source.local.entity.StudentEntity
import com.k4nd4.core.data.source.remote.RemoteDataSource
import com.k4nd4.core.data.source.remote.network.ApiResponse
import com.k4nd4.core.data.source.remote.response.StudentResponse
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.domain.repository.IBlueArchiveRepository
import com.k4nd4.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class BlueArchiveRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IBlueArchiveRepository {

    override fun getStudentFromAPI() : Flow<Resource<List<Student>>> = flow {
        remoteDataSource.getStudents().collect { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Empty -> emit(Resource.Empty)
                is ApiResponse.Error -> emit(Resource.Error(apiResponse.message))
                is ApiResponse.Success -> {
                    val studentResponseToStudent = DataMapper.studentResponseToStudent(apiResponse.data)
                    insertStudent(studentResponseToStudent)
                    emit(Resource.Success(studentResponseToStudent))
                }
            }
        }
    }

    override fun getAllStudent():Flow<List<Student>> = flow {
        localDataSource.getAllStudents().collect {allStudents ->
            emit(DataMapper.studentEntityToStudent(allStudents))
        }
    }

    override fun getFavoriteStudent(): Flow<List<Student>> =  flow {
        localDataSource.getFavoriteStudent().collect {allStudents ->
            emit(DataMapper.studentEntityToStudent(allStudents))
        }
    }

    override fun insertStudent(students: List<Student>) {
        CoroutineScope(Dispatchers.IO).launch {
            val studentEntities = DataMapper.studentsToStudentEntities(students)
            localDataSource.insertStudent(studentEntities)
        }
    }

    override fun updateFavoriteStudent(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            val studentEntity = DataMapper.studentToStudentEntity(student)
            localDataSource.updateStudent(studentEntity)
        }
    }
}