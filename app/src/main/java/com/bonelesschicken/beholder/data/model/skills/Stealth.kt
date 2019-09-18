package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Stealth(
    @ColumnInfo(name = "stealth_value")
    val value: Int,

    @ColumnInfo(name = "stealth_proficiency")
    val proficiency: Boolean
)
