package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Persuasion(
    @ColumnInfo(name = "persuasion_value")
    val value: Int,

    @ColumnInfo(name = "persuasion_proficiency")
    val proficiency: Boolean)
