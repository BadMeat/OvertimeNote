package com.dolan.arif.overtimenote.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dolan.arif.overtimenote.model.Menu

/**
 * Created by Bencoleng on 22/10/2019.
 */
@Database(entities = [Menu::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun menuDao(): MenuDao

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