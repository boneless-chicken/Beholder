package com.bonelesschicken.beholder.data.model.spells

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class SpellDuration(
    @ColumnInfo(name = "timeUnit")
    @SerializedName("timeUnit")
    val timeUnit: String,

    @ColumnInfo(name = "timeAmount")
    @SerializedName("timeAmount")
    val timeAmount: Int)
