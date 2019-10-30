package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.Acrobatics
import com.bonelesschicken.beholder.data.model.skills.SleightOfHand
import com.bonelesschicken.beholder.data.model.skills.Stealth
import com.google.gson.annotations.SerializedName

data class Dexterity(
    @SerializedName("passiveCheck")
    @ColumnInfo(name = "dexterity_passiveCheck")
    val passiveCheck: Int,

    @SerializedName("abilityModifier")
    @ColumnInfo(name = "dexterity_abilityModifier")
    val abilityModifier: Int,

    @SerializedName("abilityScore")
    @ColumnInfo(name = "dexterity_abilityScore")
    val abilityScore: Int,

    @Embedded
    @SerializedName("acrobatics")
    val acrobatics: Acrobatics,

    @Embedded
    @SerializedName("sleightOfHand")
    val sleightOfHand: SleightOfHand,

    @Embedded
    @SerializedName("stealth")
    val stealth: Stealth
)
