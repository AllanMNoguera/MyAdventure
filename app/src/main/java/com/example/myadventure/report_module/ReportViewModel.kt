package com.example.myadventure.report_module

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel;
import com.example.myadventure.R
import com.example.myadventure.database.GameDatabaseDao
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.*

class ReportViewModel(
    val database: GameDatabaseDao,
    var spaceChart: BarChart,
    var detectiveChart: BarChart) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private fun setupBarChartData() {
        // create BarEntry for Bar Group
        val bargroup = ArrayList<BarEntry>()
        bargroup.add(BarEntry(0f, 30f, "0"))
        bargroup.add(BarEntry(1f, 2f, "1"))
        bargroup.add(BarEntry(2f, 4f, "2"))
        bargroup.add(BarEntry(3f, 6f, "3"))
        bargroup.add(BarEntry(4f, 8f, "4"))
        bargroup.add(BarEntry(5f, 10f, "5"))
        bargroup.add(BarEntry(6f, 22f, "6"))
        bargroup.add(BarEntry(7f, 12.5f, "7"))
        bargroup.add(BarEntry(8f, 22f, "8"))
        bargroup.add(BarEntry(9f, 32f, "9"))
        bargroup.add(BarEntry(10f, 54f, "10"))
        bargroup.add(BarEntry(11f, 28f, "11"))

        // creating dataset for Bar Group
        val barDataSet = BarDataSet(bargroup, "")

        //barDataSet.color = ContextCompat.getColor(this, R.color.background_material_dark)

        val data = BarData(barDataSet)
        spaceChart.setData(data)
        spaceChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        spaceChart.xAxis.labelCount = 11
        spaceChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        spaceChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        spaceChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        spaceChart.description.isEnabled = false
        spaceChart.animateY(1000)
        spaceChart.legend.isEnabled = false
        spaceChart.setPinchZoom(true)
        spaceChart.data.setDrawValues(false)
    }

    init {
        setupBarChartData()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
