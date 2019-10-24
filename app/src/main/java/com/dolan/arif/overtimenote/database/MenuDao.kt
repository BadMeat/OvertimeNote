package com.dolan.arif.overtimenote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dolan.arif.overtimenote.model.Menu

/**
 * Created by Bencoleng on 22/10/2019.
 */
@Dao
interface MenuDao {

    @Insert
    suspend fun save(vararg menu: Menu)

    @Update
    suspend fun update(menu: Menu)

    @Query("select * from Menu")
    suspend fun select(): List<Menu>

    @Query("select * from Menu where date = :tgl")
    suspend fun findByDate(tgl: String): List<Menu>

    @Query("select * from Menu group by date")
    suspend fun findByDate(): List<Menu>

    @Query("select * from Menu where id = :id")
    suspend fun findById(id: Int): Menu

}