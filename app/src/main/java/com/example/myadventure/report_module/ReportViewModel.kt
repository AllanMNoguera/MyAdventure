package com.example.myadventure.report_module

import android.widget.TextView
import androidx.lifecycle.ViewModel;
import com.example.myadventure.database.GameDatabaseDao
import com.github.mikephil.charting.charts.BarChart
import kotlinx.coroutines.*

class ReportViewModel(
    val database: GameDatabaseDao,
    var spaceChart: BarChart,
    var detectiveChart: BarChart) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
