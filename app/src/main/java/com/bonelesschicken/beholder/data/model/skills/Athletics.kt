package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Athletics(
    @SerializedName("value")
    @ColumnInfo(name = "athletic_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "athletic_proficiency")
    val proficiency: Boolean
)
