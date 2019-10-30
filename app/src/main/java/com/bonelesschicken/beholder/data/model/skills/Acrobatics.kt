package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Acrobatics (
    @SerializedName("value")
    @ColumnInfo(name = "acrobatics_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "acrobatics_proficiency")
    val proficiency: Boolean
)
