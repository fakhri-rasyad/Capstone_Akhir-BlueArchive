package com.k4nd4.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListStudentResponse(

    @field:SerializedName("dataAllPage")
	val dataAllPage: Int? = null,

    @field:SerializedName("data")
	val data: List<StudentResponse>,

    @field:SerializedName("message")
	val message: String? = null
)

