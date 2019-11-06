package com.bonelesschicken.beholder.data.model.spells

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class SpellCasting(
    @ColumnInfo(name = "castingAmount")
    @SerializedName("castingAmount")
    val castingAmount: Int,

    @ColumnInfo(name = "castingType")
    @SerializedName("castingType")
    val castingType: String
)
