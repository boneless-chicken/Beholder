package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Persuasion(
    @SerializedName("value")
    @ColumnInfo(name = "persuasion_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "persuasion_proficiency")
    val proficiency: Boolean)
