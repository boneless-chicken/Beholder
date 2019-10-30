package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class HitDice(
    @SerializedName("diceRoll")
    @ColumnInfo(name = "diceRoll")
    val diceRoll: Int,

    @SerializedName("diceValue")
    @ColumnInfo(name = "diceValue")
    val diceValue: Int)
