package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Stealth(
    @SerializedName("value")
    @ColumnInfo(name = "stealth_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "stealth_proficiency")
    val proficiency: Boolean
)
