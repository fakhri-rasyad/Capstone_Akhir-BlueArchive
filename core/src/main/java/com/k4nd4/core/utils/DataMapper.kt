package com.k4nd4.core.utils

import com.k4nd4.core.data.source.local.entity.StudentEntity
import com.k4nd4.core.data.source.remote.response.StudentResponse
import com.k4nd4.core.domain.model.Student

object DataMapper {
    fun studentResponseToStudent(studentResponse: List<StudentResponse>) : List<Student> =  studentResponse.map {
        Student(
            id = it.id ?: "",
            name = it.name ?: "",
            photo = it.photoUrl ?: "",
            schoolName = it.school ?: "",
            schoolLogo = it.imageSchool ?: "",
            birthday = it.birthday ?: "",
            isFavorite = false
        )
    }
    fun studentEntityToStudent(studentEntities: List<StudentEntity>) : List<Student> = studentEntities.map {
        Student(
            id = it.id,
            name = it.name,
            photo = it.photo,
            schoolName = it.schoolName,
            schoolLogo = it.schoolLogo,
            birthday = it.birthday,
            isFavorite = it.isFavorite
        )
    }

    fun studentsToStudentEntities(students: List<Student>) = students.map { entity -> studentToStudentEntity(entity) }

    fun studentToStudentEntity(student: Student) = StudentEntity(
        id = student.id,
        name = student.name,
        photo = student.photo,
        schoolName = student.schoolName,
        schoolLogo = student.schoolLogo,
        birthday = student.birthday,
        isFavorite = student.isFavorite
    )
}