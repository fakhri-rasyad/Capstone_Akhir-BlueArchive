package com.k4nd4.core.presentation.rvadapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.databinding.StudentRvItemBinding

class StudentRecyclerViewAdapter(
    private val studentList: List<Student>,
    private val navigateOnClick:  (Student) -> Unit,
): RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(binding : StudentRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val studentPhoto = binding.studentPhoto
        val studentName = binding.studentName
        val schoolLogo = binding.schoolLogo
        val schoolName = binding.schoolName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StudentRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentList[position]
        holder.apply {
            Glide
                .with(this.itemView.context)
                .load(student.photo)
                .into(this.studentPhoto)
            studentName.text = student.name
            Glide
                .with(itemView.context)
                .load(student.schoolLogo)
                .into(schoolLogo)
            schoolName.text = student.schoolName

            itemView.setOnClickListener {
                navigateOnClick(student)
            }
        }

    }
}