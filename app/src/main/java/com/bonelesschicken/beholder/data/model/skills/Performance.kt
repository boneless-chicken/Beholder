package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Performance(
    @ColumnInfo(name = "performance_value")
    val value: Int,

    @ColumnInfo(name = "performance_proficiency")
    val proficiency: Boolean)
