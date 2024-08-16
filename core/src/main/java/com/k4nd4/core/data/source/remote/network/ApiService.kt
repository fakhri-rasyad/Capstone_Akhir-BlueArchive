package com.k4nd4.core.data.source.remote.network

import com.k4nd4.core.data.source.remote.response.ListStudentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/characters")
    suspend fun getStudents(
        @Query("perPage") perPage : String = "40"
    ) : ListStudentResponse
}