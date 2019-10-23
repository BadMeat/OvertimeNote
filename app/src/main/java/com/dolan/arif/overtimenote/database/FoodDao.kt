package com.dolan.arif.overtimenote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dolan.arif.overtimenote.model.Food

@Dao
interface FoodDao {

    @Insert
    suspend fun insert(vararg food: Food): List<Long>

    @Query("select * from Food")
    suspend fun select(): List<Food>
}