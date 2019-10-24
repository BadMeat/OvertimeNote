package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Food
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : BaseViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()

    fun save(e: Food) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).foodDao()
            dao.insert(e)
            Toast.makeText(getApplication(), "Save Success", Toast.LENGTH_SHORT).show()
            isLoading.value = false
        }
    }

}