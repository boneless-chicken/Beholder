package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Religion(
    @SerializedName("value")
    @ColumnInfo(name = "religion_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "religion_proficiency")
    val proficiency: Boolean
)
