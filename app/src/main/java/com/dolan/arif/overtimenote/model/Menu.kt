package com.dolan.arif.overtimenote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Bencoleng on 22/10/2019.
 */
@Entity
data class Menu(
    @ColumnInfo(name = "person")
    val person: String?,
    @ColumnInfo(name = "food")
    val food: String?,
    @ColumnInfo(name = "date")
    val date: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}