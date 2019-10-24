package com.dolan.arif.overtimenote.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Bencoleng on 22/10/2019.
 */
@Entity
@Parcelize
data class Menu(
    @ColumnInfo(name = "person")
    val person: String?,
    @ColumnInfo(name = "food")
    val food: String?,
    @ColumnInfo(name = "date")
    val date: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0
}