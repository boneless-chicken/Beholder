package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Performance(
    @SerializedName("value")
    @ColumnInfo(name = "performance_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "performance_proficiency")
    val proficiency: Boolean)
