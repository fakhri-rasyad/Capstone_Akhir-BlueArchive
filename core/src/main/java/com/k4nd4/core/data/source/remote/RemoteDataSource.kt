package com.k4nd4.core.data.source.remote

import com.k4nd4.core.data.source.remote.network.ApiResponse
import com.k4nd4.core.data.source.remote.network.ApiService
import com.k4nd4.core.data.source.remote.response.StudentResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getStudents(): Flow<ApiResponse<List<StudentResponse>>> = flow {
        try {
            val response = apiService.getStudents()
            val studentList = response.data
            if (studentList.isNotEmpty()){
                emit(ApiResponse.Success(studentList))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }
}