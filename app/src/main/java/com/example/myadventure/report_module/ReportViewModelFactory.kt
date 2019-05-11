package com.example.myadventure.report_module

import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myadventure.database.GameDatabaseDao

class ReportViewModelFactory(
    private val dataSource: GameDatabaseDao,
    private val editText: TextView) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
                return ReportViewModel(dataSource, editText) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}