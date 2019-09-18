package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.Acrobatics
import com.bonelesschicken.beholder.data.model.skills.SleightOfHand
import com.bonelesschicken.beholder.data.model.skills.Stealth

data class Dexterity(
    @ColumnInfo(name = "dexterity_passiveCheck")
    val passiveCheck: Int,

    @ColumnInfo(name = "dexterity_abilityModifier")
    val abilityModifier: Int,

    @ColumnInfo(name = "dexterity_abilityScore")
    val abilityScore: Int,

    @Embedded
    val acrobatics: Acrobatics,

    @Embedded
    val sleightOfHand: SleightOfHand,

    @Embedded
    val stealth: Stealth
)
