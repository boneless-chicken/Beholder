package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Acrobatics (
    @ColumnInfo(name = "acrobatics_value")
    val value: Int,

    @ColumnInfo(name = "acrobatics_proficiency")
    val proficiency: Boolean
)
