package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.CountWrapper
import kotlinx.coroutines.launch

class MenuCountViewModel(application: Application) : BaseViewModel(application) {

    val countList = MutableLiveData<List<CountWrapper>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getData(date: String) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            Log.d("DATAU","${dao.findCountByDate(date)}")
            countList.value = dao.findCountByDate(date)
            isLoading.value = false
        }
    }

}