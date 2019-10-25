package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Report
import kotlinx.coroutines.launch
import me.ithebk.barchart.BarChartModel
import me.ithebk.barchart.BarChartUtils.getRandomColor

/**
 * Created by Bencoleng on 24/10/2019.
 */
class ReportViewModel(application: Application) : BaseViewModel(application) {

    val bartChartList = MutableLiveData<List<BarChartModel>>()

    fun findAll() {
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).reportDao()
            val result = dao.select()
            val temp = mutableListOf<BarChartModel>()
            for (e: Report in result) {
                val barChartModel = BarChartModel()
                barChartModel.barValue = e.total
                barChartModel.barColor = getRandomColor()
                val decimalDigit = if (e.total >= 1000) {
                    val totalK: Double = e.total.toDouble() / 1000
                    String.format("%.1f K", totalK)
                } else {
                    e.total.toString()
                }

                barChartModel.barText = "${e.date}\n$decimalDigit"
                barChartModel.barTag = e.date
                temp.add(barChartModel)
            }
            bartChartList.value = temp
        }
    }

}