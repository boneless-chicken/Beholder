package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Perception(
    @ColumnInfo(name = "perception_value")
    val value: Int,

    @ColumnInfo(name = "perception_proficiency")
    val proficiency: Boolean)
