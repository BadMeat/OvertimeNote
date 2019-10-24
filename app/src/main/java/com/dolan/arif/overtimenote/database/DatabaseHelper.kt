package com.dolan.arif.overtimenote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dolan.arif.overtimenote.model.Food
import com.dolan.arif.overtimenote.model.Menu
import com.dolan.arif.overtimenote.model.Person
import com.dolan.arif.overtimenote.model.Report

/**
 * Created by Bencoleng on 22/10/2019.
 */
@Database(entities = [Menu::class, Person::class, Food::class, Report::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun menuDao(): MenuDao
    abstract fun personDao(): PersonDao
    abstract fun foodDao(): FoodDao
    abstract fun reportDao(): ReportDao

    companion object {

        var instance: DatabaseHelper? = null

        operator fun invoke(ctx: Context) = instance ?: synchronized(Any()) {
            instance ?: createDb(ctx).also {
                instance = it
            }
        }

        private fun createDb(ctx: Context) = Room.databaseBuilder(
            ctx.applicationContext, DatabaseHelper::class.java, "overtimenote"
        ).build()
    }

}