package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Religion(
    @ColumnInfo(name = "religion_value")
    val value: Int,

    @ColumnInfo(name = "religion_proficiency")
    val proficiency: Boolean
)
