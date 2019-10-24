package com.dolan.arif.overtimenote.view.report


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dolan.arif.overtimenote.R
import kotlinx.android.synthetic.main.fragment_report.*
import me.ithebk.barchart.BarChartModel

class ReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chart.barMaxValue = 100
        addBar(50)
        addBar(70)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
        addBar(90)
    }

    private fun addBar(number : Int) {
        val barChartModel = BarChartModel()
        barChartModel.barValue = number
        barChartModel.barColor = Color.parseColor("#9C27B0")
        barChartModel.barText = "NOTHING"
        chart.addBar(barChartModel)
    }
}
