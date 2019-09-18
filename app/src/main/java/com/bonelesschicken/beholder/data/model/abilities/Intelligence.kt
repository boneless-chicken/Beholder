package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.*

data class Intelligence(
    @ColumnInfo(name = "intelligence_passiveCheck")
    val passiveCheck: Int,

    @ColumnInfo(name = "intelligence_abilityModifier")
    val abilityModifier: Int,

    @ColumnInfo(name = "intelligence_abilityScore")
    val abilityScore: Int,

    @Embedded
    val arcana: Arcana,

    @Embedded
    val history: History,

    @Embedded
    val investigation: Investigation,

    @Embedded
    val nature: Nature,

    @Embedded
    val religion: Religion
)
