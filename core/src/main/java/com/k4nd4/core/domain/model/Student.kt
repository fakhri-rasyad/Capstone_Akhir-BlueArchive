package com.k4nd4.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id : String,
    val name: String,
    val photo: String,
    val schoolName: String,
    val schoolLogo: String,
    val birthday: String,
    var isFavorite: Boolean,
) : Parcelable