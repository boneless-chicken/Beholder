package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Athletics(
    @ColumnInfo(name = "athletic_value")
    val value: Int,

    @ColumnInfo(name = "athletic_proficiency")
    val proficiency: Boolean
)
