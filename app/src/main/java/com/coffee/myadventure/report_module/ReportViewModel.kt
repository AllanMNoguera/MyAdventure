package com.coffee.myadventure.report_module

import androidx.lifecycle.ViewModel
import com.coffee.myadventure.database.GameDatabaseDao
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

    private var scores_space: IntArray = IntArray(0)

    private var scores_detective: IntArray = IntArray(0)

    private suspend fun getSpaceScores(): IntArray {
        return withContext(Dispatchers.IO) {
            var latestScores = database.getSpaceLatestScores()
            latestScores
        }
    }

    private suspend fun getDetectiveScores(): IntArray {
        return withContext(Dispatchers.IO) {
            var latestScores = database.getDetectiveLatestScores()
            latestScores
        }
    }

    private fun setupBarChartData() {
        // create BarEntry for Bar Group
        uiScope.launch {
            scores_space = getSpaceScores()
            val bargroupSpace = ArrayList<BarEntry>()
            var id_space = 0f;
            for (score in scores_space.reversedArray()) {
                bargroupSpace.add(BarEntry(id_space, score.toFloat(), id_space.toString()))
                id_space++
            }

            val barDataSetSpace = BarDataSet(bargroupSpace, "")

            val dataSpace = BarData(barDataSetSpace)
            spaceChart.setData(dataSpace)
            spaceChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            spaceChart.xAxis.labelCount = id_space.toInt()
            spaceChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
            spaceChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
            spaceChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
            spaceChart.description.isEnabled = false
            spaceChart.animateY(1000)
            spaceChart.legend.isEnabled = false
            spaceChart.setPinchZoom(true)
            spaceChart.data.setDrawValues(false)

            scores_detective = getDetectiveScores()
            val bargroupDetective = ArrayList<BarEntry>()
            var id_detective = 0f;
            for (score in scores_detective.reversedArray()) {
                bargroupDetective.add(BarEntry(id_detective, score.toFloat(), id_detective.toString()))
                id_detective++
            }

            val barDataSetDetective = BarDataSet(bargroupDetective, "")

            val dataDetective = BarData(barDataSetDetective)
            detectiveChart.setData(dataDetective)
            detectiveChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            detectiveChart.xAxis.labelCount = id_detective.toInt()
            detectiveChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
            detectiveChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
            detectiveChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
            detectiveChart.description.isEnabled = false
            detectiveChart.animateY(1000)
            detectiveChart.legend.isEnabled = false
            detectiveChart.setPinchZoom(true)
            detectiveChart.data.setDrawValues(false)
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
