package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Insight(
    @ColumnInfo(name = "insight_value")
    val value: Int,

    @ColumnInfo(name = "insight_proficiency")
    val proficiency: Boolean)
