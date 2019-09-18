package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.Athletics

data class Strength(
    @ColumnInfo(name = "strength_passiveCheck")
    val passiveCheck: Int,

    @ColumnInfo(name = "strength_proficiencyBonus")
    val abilityModifier: Int,

    @ColumnInfo(name = "strength_abilityScore")
    val abilityScore: Int,

    @Embedded
    val athletics: Athletics)
