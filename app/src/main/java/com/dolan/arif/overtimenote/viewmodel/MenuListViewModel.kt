package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Menu
import kotlinx.coroutines.launch

class MenuListViewModel(application: Application) : BaseViewModel(application) {

    val menuList = MutableLiveData<List<Menu>>()
    val isLoading = MutableLiveData<Boolean>()

    fun findByDate(date : String) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            menuList.value = dao.findByDate(date)
            isLoading.value = false
        }
    }

    fun saveData(e: Menu) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            dao.save(e)
            isLoading.value = false
        }
    }
}