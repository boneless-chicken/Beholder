package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Deception(
    @SerializedName("value")
    @ColumnInfo(name = "deception_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "deception_proficiency")
    val proficiency: Boolean)
