package com.bonelesschicken.beholder.data.model.spells

import com.google.gson.annotations.SerializedName

data class SpellSlot(
    @SerializedName("level")
    val level: Int,
    @SerializedName("quantity")
    val quantity: Int
)