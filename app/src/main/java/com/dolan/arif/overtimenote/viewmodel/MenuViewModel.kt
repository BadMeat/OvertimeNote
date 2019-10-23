package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Menu
import kotlinx.coroutines.launch

/**
 * Created by Bencoleng on 22/10/2019.
 */
class MenuViewModel(application: Application) : BaseViewModel(application) {

    val menuList = MutableLiveData<List<Menu>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getData() {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            menuList.value = dao.select()
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