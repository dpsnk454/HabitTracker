package com.example.assignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        // Mock data for statistics
        val longestStreak = 7
        val completionRate = 85

        // Set text data
        findViewById<TextView>(R.id.textStreak).text = "Longest Streak: $longestStreak"
        findViewById<TextView>(R.id.textCompletionRate).text = "Completion Rate: $completionRate%"

        // Create a line chart for habit completion over time
        val habitChart = findViewById<LineChart>(R.id.habitChart)
        val habitEntries = listOf(
            Entry(1f, 2f),
            Entry(2f, 4f),
            Entry(3f, 3f),
            Entry(4f, 5f),
            Entry(5f, 6f)
        )

        val habitDataSet = LineDataSet(habitEntries, "Habit Completion Over Time")
        habitDataSet.color = resources.getColor(R.color.teal_700, null)
        habitDataSet.valueTextColor = resources.getColor(R.color.black, null)

        val habitLineData = LineData(habitDataSet)
        habitChart.data = habitLineData

        // Style the habit chart
        habitChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        habitChart.axisRight.isEnabled = false
        habitChart.invalidate() // Refresh chart

        // Create a pie chart for completion rate
        val completionRateChart = findViewById<PieChart>(R.id.completionRateChart)
        val pieEntries = mutableListOf<PieEntry>()
        pieEntries.add(PieEntry(completionRate.toFloat(), "Completed"))
        pieEntries.add(PieEntry(100 - completionRate.toFloat(), "Remaining"))

        val pieDataSet = PieDataSet(pieEntries, "Habit Completion")
        pieDataSet.colors = listOf(
            resources.getColor(R.color.teal_700, null),
            resources.getColor(R.color.gray, null)
        )
        pieDataSet.valueTextColor = resources.getColor(R.color.black, null)

        val pieData = PieData(pieDataSet)
        completionRateChart.data = pieData
        completionRateChart.invalidate() // Refresh chart
    }
}
