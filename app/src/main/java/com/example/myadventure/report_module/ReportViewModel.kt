package com.example.myadventure.report_module

import androidx.lifecycle.ViewModel
import com.example.myadventure.database.Game
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

    private var scores: IntArray = IntArray(0)

    private suspend fun getSpaceScores(): IntArray {
        return withContext(Dispatchers.IO) {
            var latestScores = database.getSpaceLatestScores()
            latestScores
        }
    }

    private fun setupBarChartData() {
        // create BarEntry for Bar Group
        uiScope.launch {
            scores = getSpaceScores()
            System.out.println(scores[0])
            val bargroup = ArrayList<BarEntry>()
            var id = 0f;
            for (score in scores.reversedArray()) {
                bargroup.add(BarEntry(id, score.toFloat(), id.toString()))
                id++
            }
            // creating dataset for Bar Group
            val barDataSet = BarDataSet(bargroup, "")

            //barDataSet.color = ContextCompat.getColor(this, R.color.background_material_dark)

            val data = BarData(barDataSet)
            spaceChart.setData(data)
            spaceChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            spaceChart.xAxis.labelCount = id.toInt()
            spaceChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
            spaceChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
            spaceChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
            spaceChart.description.isEnabled = false
            spaceChart.animateY(1000)
            spaceChart.legend.isEnabled = false
            spaceChart.setPinchZoom(true)
            spaceChart.data.setDrawValues(false)
        }
    }

    init {
        setupBarChartData()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
