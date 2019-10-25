package com.dolan.arif.overtimenote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dolan.arif.overtimenote.model.Person

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(vararg person: Person): List<Long>

    @Query("select * from Person  order by name")
    suspend fun select(): List<Person>

}