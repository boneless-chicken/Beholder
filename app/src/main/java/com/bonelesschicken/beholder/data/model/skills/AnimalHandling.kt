package com.bonelesschicken.beholder.data.model.skills

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class AnimalHandling(
    @SerializedName("value")
    @ColumnInfo(name = "animalHandling_value")
    val value: Int,

    @SerializedName("proficiency")
    @ColumnInfo(name = "animalHandling_proficiency")
    val proficiency: Boolean)