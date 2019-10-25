package com.dolan.arif.overtimenote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dolan.arif.overtimenote.model.Food

@Dao
interface FoodDao {

    @Insert
    suspend fun insert(vararg food: Food): List<Long>

    @Update
    suspend fun update(food: Food)

    @Query("select * from Food order by name")
    suspend fun select(): List<Food>

    @Query("select * from Food where name = :name")
    suspend fun findByName(name: String): Food?
}