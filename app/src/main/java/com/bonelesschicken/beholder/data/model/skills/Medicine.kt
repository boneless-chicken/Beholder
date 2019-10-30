package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Medicine(
    @SerializedName("value")
    @ColumnInfo(name = "medicine_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "medicine_proficiency")
    val proficiency: Boolean)
