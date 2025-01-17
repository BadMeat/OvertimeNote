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
            val data = dao.findByDate()
            data.let {
                menuList.value = it
            }

            isLoading.value = false
        }
    }
}