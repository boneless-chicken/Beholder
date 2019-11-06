package com.bonelesschicken.beholder.data.model.spells

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class SpellRange(
    @ColumnInfo(name = "aoeType")
    @SerializedName("aoeType")
    val aoeType: String,

    @ColumnInfo(name = "area")
    @SerializedName("area")
    val area: Int,

    @ColumnInfo(name = "range")
    @SerializedName("range")
    val range: Int)
