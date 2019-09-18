package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class Medicine(
    @ColumnInfo(name = "medicine_value")
    val value: Int,

    @ColumnInfo(name = "medicine_proficiency")
    val proficiency: Boolean)
