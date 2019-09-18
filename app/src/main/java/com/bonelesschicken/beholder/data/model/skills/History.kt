package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class History(
    @ColumnInfo(name = "history_value")
    val value: Int,

    @ColumnInfo(name = "history_proficiency")
    val proficiency: Boolean
)
