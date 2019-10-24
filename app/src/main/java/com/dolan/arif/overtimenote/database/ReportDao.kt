package com.dolan.arif.overtimenote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dolan.arif.overtimenote.model.Report

@Dao
interface ReportDao {

    @Insert
    suspend fun insert(vararg report: Report): List<Long>

    @Update
    suspend fun update(report: Report)

    @Query("select * from Report")
    suspend fun select(): List<Report>

    @Query("select * from Report where date = :date")
    suspend fun findByDate(date: String): Report?
}