package com.coffee.myadventure.report_module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coffee.myadventure.database.GameDatabaseDao
import com.github.mikephil.charting.charts.BarChart

class ReportViewModelFactory(
    private val dataSource: GameDatabaseDao,
    private val spaceChart: BarChart,
    private val detectiveChart: BarChart) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
                return ReportViewModel(dataSource, spaceChart, detectiveChart) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}