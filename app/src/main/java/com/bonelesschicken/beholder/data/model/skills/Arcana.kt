package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Arcana(
    @SerializedName("value")
    @ColumnInfo(name = "arcana_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "arcana_proficiency")
    val proficiency: Boolean
)
