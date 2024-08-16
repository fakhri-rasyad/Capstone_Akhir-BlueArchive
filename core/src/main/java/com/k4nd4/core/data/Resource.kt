package com.k4nd4.core.data

import com.k4nd4.core.data.source.remote.response.StudentResponse
import com.k4nd4.core.domain.model.Student

sealed class Resource<out R> {
    data class Success(val data: List<Student>) : Resource<List<Student>>()
    data class Error(val message: String) : Resource<Nothing>()
    data object Empty : Resource<Nothing>()
}