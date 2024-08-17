package com.k4nd4.capstone.screen

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.k4nd4.capstone.R
import com.k4nd4.capstone.databinding.ActivityDetailBinding
import com.k4nd4.capstone.viewmodel.DetailViewModel
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object {
        const val STUDENT = "student"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val student = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(STUDENT, Student::class.java)
        } else {
            intent.getParcelableExtra(STUDENT)
        }

        binding.apply {
            if (student != null) {
                Glide
                    .with(this@DetailActivity)
                    .load(student.photo)
                    .into(studentPhoto)
                Glide.with(this@DetailActivity)
                    .load(student.schoolLogo)
                    .into(schoolLogo)
                schoolName.text = student.schoolName
                studentName.text = student.name
                birthdayDate.text = student.birthday
            }
            backButton.setOnClickListener {
                finish()
            }
        }
        viewModel.updateIsFavorite(student!!.isFavorite)
        viewModel.isFavorite.observe(this) { isFavorite ->
            setUpFAB(isFavorite)
            binding.favoriteButton.setOnClickListener {
                viewModel.updateStudent(student, !isFavorite)
                val changeStatusDesc =
                    if (isFavorite) getString(R.string.removed_student) else getString(
                        R.string.added_student
                    )
                showToast(changeStatusDesc, this)
            }
        }
    }

    private fun setUpFAB(newState: Boolean) {
        val resId =
            if (newState) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
        binding.favoriteButton.setImageResource(resId)
    }
}