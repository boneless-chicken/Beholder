package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo

data class AnimalHandling(
    @ColumnInfo(name = "animalHandling_value")
    val value: Int,

    @ColumnInfo(name = "animalHandling_proficiency")
    val proficiency: Boolean)