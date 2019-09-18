package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.*

data class Wisdom(
    @ColumnInfo(name = "wisdom_passiveCheck")
    val passiveCheck: Int,

    @ColumnInfo(name = "wisdom_abilityModifier")
    val abilityModifier: Int,

    @ColumnInfo(name = "wisdom_abilityScore")
    val abilityScore: Int,

    @Embedded
    val animalHandling: AnimalHandling,

    @Embedded
    val insight: Insight,

    @Embedded
    val medicine: Medicine,

    @Embedded
    val perception: Perception,

    @Embedded
    val survival: Survival)
