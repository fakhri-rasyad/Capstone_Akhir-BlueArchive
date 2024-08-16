package com.k4nd4.core.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.k4nd4.core.domain.model.Student

fun showToast(message: String, context: Context){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}
interface IntentProvider {
    fun getDetailActivityIntent(context: Context, student: Student): Intent
}

