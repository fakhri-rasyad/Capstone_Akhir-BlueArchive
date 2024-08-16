package com.k4nd4.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentEntity(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "photo")
    val photo: String,

    @ColumnInfo(name = "schoolName")
    val schoolName: String,

    @ColumnInfo(name = "schoolLogo")
    val schoolLogo: String,

    @ColumnInfo(name = "birthday")
    val birthday: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)