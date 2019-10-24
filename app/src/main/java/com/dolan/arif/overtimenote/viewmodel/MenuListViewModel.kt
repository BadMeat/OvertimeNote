package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.model.Report
import kotlinx.coroutines.launch

class MenuListViewModel(application: Application) : BaseViewModel(application) {

    val menuList = MutableLiveData<List<Menu>>()
    val isLoading = MutableLiveData<Boolean>()
    val report = MutableLiveData<Report>()
    private val isUpdate = MutableLiveData<Boolean>()

    fun findByDate(date: String) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            menuList.value = dao.findByDate(date)
            isLoading.value = false
        }
    }

    fun saveReport(report: Report) {
        isLoading.value = true
        launch {
            Log.d("REPORTNYA", "$report")
            val dao = DatabaseHelper.invoke(getApplication()).reportDao()
            var message = ""
            isUpdate.value?.let {
                message = if (it) {
                    dao.update(report)
                    "Update Success"
                } else {
                    dao.insert(report)
                    "Save Success"
                }
            }
            Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
            isLoading.value = false
        }
    }

    fun findReportByDate(date: String) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).reportDao()
            val result = dao.findByDate(date)
            Log.d("REULSTNYA", "$result")
            isUpdate.value = result != null
            report.value = result
            isLoading.value = false
        }
    }
}