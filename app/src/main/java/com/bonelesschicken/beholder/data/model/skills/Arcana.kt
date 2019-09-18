package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Arcana(
    @ColumnInfo(name = "arcana_value")
    val value: Int,

    @ColumnInfo(name = "arcana_proficiency")
    val proficiency: Boolean
)
