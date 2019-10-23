package com.dolan.arif.overtimenote.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dolan.arif.overtimenote.database.DatabaseHelper
import com.dolan.arif.overtimenote.model.Food
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.model.Person
import kotlinx.coroutines.launch

class MenuAddViewModel(application: Application) : BaseViewModel(application) {

    val personList = MutableLiveData<List<Person>>()
    val person = MutableLiveData<Person>()

    val isLoading = MutableLiveData<Boolean>()
    val menu = MutableLiveData<Menu>()

    val foodList = MutableLiveData<List<Food>>()
    val food = MutableLiveData<Food>()

    fun setPerson(e: Person) {
        person.value = e
    }

    fun setFood(e: Food) {
        food.value = e
    }

    fun saveMenu(e: Menu) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            dao.save(e)
            isLoading.value = false
            Toast.makeText(getApplication(), "Save Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun getDataPerson() {
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).personDao()
            personList.value = dao.select()
        }
    }

    fun getDataFood() {
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).foodDao()
            foodList.value = dao.select()
        }
    }

}