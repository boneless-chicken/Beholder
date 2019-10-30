package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Nature(
    @SerializedName("value")
    @ColumnInfo(name = "nature_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "nature_proficiency")
    val proficiency: Boolean
)
