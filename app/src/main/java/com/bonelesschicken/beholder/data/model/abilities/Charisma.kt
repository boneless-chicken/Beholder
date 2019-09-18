package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.Deception
import com.bonelesschicken.beholder.data.model.skills.Intimidation
import com.bonelesschicken.beholder.data.model.skills.Performance
import com.bonelesschicken.beholder.data.model.skills.Persuasion

data class Charisma(
    @ColumnInfo(name = "charisma_passiveCheck")
    val passiveCheck: Int,

    @ColumnInfo(name = "charisma_abilityModifier")
    val abilityModifier: Int,

    @ColumnInfo(name = "charisma_abilityScore")
    val abilityScore: Int,

    @Embedded
    val deception: Deception,

    @Embedded
    val intimidation: Intimidation,

    @Embedded
    val performance: Performance,

    @Embedded
    val persuasion: Persuasion)
