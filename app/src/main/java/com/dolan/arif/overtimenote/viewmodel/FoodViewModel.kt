package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import android.widget.Toast
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Food
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : BaseViewModel(application) {

    fun save(e: Food) {
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).foodDao()
            dao.insert(e)
            Toast.makeText(getApplication(), "Save Success", Toast.LENGTH_SHORT).show()
        }
    }

}