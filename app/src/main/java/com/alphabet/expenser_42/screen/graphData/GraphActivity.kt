package com.alphabet.expenser_42.screen.graphData

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alphabet.expenser_42.databinding.ActivityGraphBinding
import com.alphabet.expenser_42.model.GraphDataModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class GraphActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var arrData = ArrayList<GraphDataModel>().apply{
            add(GraphDataModel("1", "1000"))
            add(GraphDataModel("2", "500"))
            add(GraphDataModel("3", "2000"))
            add(GraphDataModel("4", "2500"))
        }
        var entries = ArrayList<BarEntry>()
        for (data in arrData) {
            // turn your data into Entry objects
            entries.add(BarEntry(data.month.toFloat(), data.expense.toFloat()));
        }

        val dataSet = BarDataSet(entries, "Expense Monthly")

        val barData = BarData(dataSet)

        //line data
        var lineEntries = ArrayList<Entry>()
        for (data in arrData) {
            // turn your data into Entry objects
            lineEntries.add(Entry(data.month.toFloat(), data.expense.toFloat()));
        }

        val lineDataSet = LineDataSet(lineEntries, "Expense Monthly")

        val lineData = LineData(lineDataSet)


        with(binding){

            barChart.data = barData
            barChart.invalidate()

            lineChart.data = lineData
            lineChart.invalidate()

        }


    }
}