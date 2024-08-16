package com.k4nd4.capstone.utils

import android.content.Context
import android.content.Intent
import com.k4nd4.capstone.screen.DetailActivity
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.utils.IntentProvider

class AppIntentProvider : IntentProvider {
    override fun getDetailActivityIntent(context: Context, student: Student): Intent {
        return Intent(context, DetailActivity::class.java).apply {
            putExtra(DetailActivity.STUDENT, student)
        }
    }
}