package com.example.myadventure.report_module

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myadventure.database.GameDatabaseDao

class ReportLoginViewModelFactory(
    private val dataSource: GameDatabaseDao,
    private val editText: EditText) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportLoginViewModel::class.java)) {
            return ReportLoginViewModel(dataSource, editText) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}