package com.dolan.arif.overtimenote.model

import androidx.room.ColumnInfo

data class CountWrapper(
    @ColumnInfo(name = "jumlah")
    var count: Int?,
    var food: String?
)