package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo

data class Constitution(
    @ColumnInfo(name = "constitution_passiveCheck")
    val passiveCheck: Int,

    @ColumnInfo(name = "constitution_abilityModifier")
    val abilityModifier: Int,

    @ColumnInfo(name = "constitution_abilityScore")
    val abilityScore: Int)
