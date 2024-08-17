package com.k4nd4.core.data.source.remote.network

import com.k4nd4.core.data.source.remote.response.StudentResponse

sealed class ApiResponse<out R> {
    data class Success(val data: List<StudentResponse>) : ApiResponse<List<StudentResponse>>()
    data class Error(val message: String) : ApiResponse<Nothing>()
    data object Empty : ApiResponse<Nothing>()
}