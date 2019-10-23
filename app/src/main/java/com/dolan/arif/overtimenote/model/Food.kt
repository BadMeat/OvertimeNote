package com.dolan.arif.overtimenote.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}