package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Investigation(
    @ColumnInfo(name = "investigation_value")
    val value: Int,

    @ColumnInfo(name = "investigation_proficiency")
    val proficiency: Boolean
)