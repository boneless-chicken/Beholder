package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Intimidation(
    @ColumnInfo(name = "intimidation_value")
    val value: Int,

    @ColumnInfo(name = "intimidation_proficiency")
    val proficiency: Boolean)
