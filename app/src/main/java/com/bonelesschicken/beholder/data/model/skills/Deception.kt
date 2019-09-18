package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Deception(
    @ColumnInfo(name = "deception_value")
    val value: Int,

    @ColumnInfo(name = "deception_proficiency")
    val proficiency: Boolean)
