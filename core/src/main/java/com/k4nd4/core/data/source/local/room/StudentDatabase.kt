package com.k4nd4.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.k4nd4.core.data.source.local.entity.StudentEntity

@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao() : StudentDao
}