package com.dolan.arif.overtimenote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Report(
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "total")
    val total: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}