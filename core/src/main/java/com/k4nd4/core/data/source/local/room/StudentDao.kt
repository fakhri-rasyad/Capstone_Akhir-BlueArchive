package com.k4nd4.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.k4nd4.core.data.source.local.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * FROM studententity")
    fun getStudents() : Flow<List<StudentEntity>>

    @Query("SELECT * FROM studententity WHERE isFavorite = 1")
    fun getFavoriteStudents() : Flow<List<StudentEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(students: List<StudentEntity>)

    @Update
    suspend fun updateStudent(student: StudentEntity)

}