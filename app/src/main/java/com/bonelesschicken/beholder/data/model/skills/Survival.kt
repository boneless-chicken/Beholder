package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

class Survival(
    @ColumnInfo(name = "survival_value")
    val value: Int,

    @ColumnInfo(name = "survival_proficiency")
    val proficiency: Boolean)