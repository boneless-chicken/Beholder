package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.*
import com.google.gson.annotations.SerializedName

data class Wisdom(
    @SerializedName("passiveCheck")
    @ColumnInfo(name = "wisdom_passiveCheck")
    val passiveCheck: Int,

    @SerializedName("abilityModifier")
    @ColumnInfo(name = "wisdom_abilityModifier")
    val abilityModifier: Int,

    @SerializedName("abilityScore")
    @ColumnInfo(name = "wisdom_abilityScore")
    val abilityScore: Int,

    @Embedded
    @SerializedName("animalHandling")
    val animalHandling: AnimalHandling,

    @Embedded
    @SerializedName("insight")
    val insight: Insight,

    @Embedded
    @SerializedName("medicine")
    val medicine: Medicine,

    @Embedded
    @SerializedName("perception")
    val perception: Perception,

    @Embedded
    @SerializedName("survival")
    val survival: Survival)
