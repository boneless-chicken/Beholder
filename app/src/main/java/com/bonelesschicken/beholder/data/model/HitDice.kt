package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo

data class HitDice(
    @ColumnInfo(name = "diceRoll")
    val diceRoll: Int,

    @ColumnInfo(name = "diceValue")
    val diceValue: Int)
