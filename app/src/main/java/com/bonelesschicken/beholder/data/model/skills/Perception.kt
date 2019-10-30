package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Perception(
    @SerializedName("value")
    @ColumnInfo(name = "perception_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "perception_proficiency")
    val proficiency: Boolean)
