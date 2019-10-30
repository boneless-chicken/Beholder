package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("value")
    @ColumnInfo(name = "history_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "history_proficiency")
    val proficiency: Boolean
)
