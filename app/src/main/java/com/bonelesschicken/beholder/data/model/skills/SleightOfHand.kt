package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class SleightOfHand (
    @ColumnInfo(name = "sleightOfHand_value")
    val value: Int,

    @ColumnInfo(name = "sleightOfHand_proficiency")
    val proficiency: Boolean
)
