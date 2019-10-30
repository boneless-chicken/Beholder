package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Insight(
    @SerializedName("value")
    @ColumnInfo(name = "insight_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "insight_proficiency")
    val proficiency: Boolean)
