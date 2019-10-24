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

    fun saveMenu(e: Menu, type: String) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            val message: String
            message = if (type.equals("update", true)) {
                dao.update(e)
                "Update Success"
            } else {
                dao.save(e)
                "Save Success"
            }
            isLoading.value = false
            Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteMenu(e: Menu) {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            dao.delete(e)
            isLoading.value = false
            Toast.makeText(getApplication(), "Delete Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun findById(id: Int) {
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).menuDao()
            menu.value = dao.findById(id)
        }
    }

    fun getDataPerson() {
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).personDao()
            personList.value = dao.select()
        }
    }

    fun getDataFood() {
        isLoading.value = true
        launch {
            val dao = DatabaseHelper.invoke(getApplication()).foodDao()
            foodList.value = dao.select()
            isLoading.value = false
        }
    }

}