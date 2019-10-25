package com.dolan.arif.overtimenote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Report(
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "total")
    var total: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0
}