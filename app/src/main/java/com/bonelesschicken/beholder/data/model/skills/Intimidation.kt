package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Intimidation(
    @SerializedName("value")
    @ColumnInfo(name = "intimidation_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "intimidation_proficiency")
    val proficiency: Boolean)
