package com.dolan.arif.overtimenote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @ColumnInfo(name = "name")
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}