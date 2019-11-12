package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Inventory(
    @SerializedName("weapons")
    @ColumnInfo(name = "weapons")
    var weapons: List<String> = ArrayList(),

    @SerializedName("armors")
    @ColumnInfo(name = "armors")
    var armors: List<String> = ArrayList(),

    @SerializedName("items")
    @ColumnInfo(name = "items")
    var items: List<String> = ArrayList()
)