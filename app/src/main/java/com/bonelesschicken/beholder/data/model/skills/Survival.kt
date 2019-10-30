package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class Survival(
    @SerializedName("value")
    @ColumnInfo(name = "survival_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "survival_proficiency")
    val proficiency: Boolean)