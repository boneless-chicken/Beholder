package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Investigation(
    @SerializedName("value")
    @ColumnInfo(name = "investigation_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "investigation_proficiency")
    val proficiency: Boolean
)