package com.k4nd4.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StudentResponse(

	@field:SerializedName("birthday")
	val birthday: String? = null,

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("school")
	val school: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("imageSchool")
	val imageSchool: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("damageType")
	val damageType: String? = null
)