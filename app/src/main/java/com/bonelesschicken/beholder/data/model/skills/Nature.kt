package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Nature(
    @ColumnInfo(name = "nature_value")
    val value: Int,

    @ColumnInfo(name = "nature_proficiency")
    val proficiency: Boolean
)
