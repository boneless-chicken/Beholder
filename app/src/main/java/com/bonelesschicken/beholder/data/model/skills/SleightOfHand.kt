package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class SleightOfHand (
    @SerializedName("value")
    @ColumnInfo(name = "sleightOfHand_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "sleightOfHand_proficiency")
    val proficiency: Boolean
)
