package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Person
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : BaseViewModel(application) {

    val isLoading = MutableLiveData<Boolean>()

    fun save(person: Person) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).personDao()
            dao.insert(person)
            isLoading.value = false
        }
    }

}