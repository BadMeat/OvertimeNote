package com.dolan.arif.overtimenote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dolan.arif.overtimenote.model.Menu

/**
 * Created by Bencoleng on 22/10/2019.
 */
@Dao
interface MenuDao {

    @Insert
    suspend fun save(vararg menu: Menu)

    @Query("select * from Menu")
    suspend fun select(): List<Menu>

}