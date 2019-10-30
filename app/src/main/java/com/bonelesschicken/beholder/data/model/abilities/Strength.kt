package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.Athletics
import com.google.gson.annotations.SerializedName

data class Strength(
    @SerializedName("passiveCheck")
    @ColumnInfo(name = "strength_passiveCheck")
    val passiveCheck: Int,

    @SerializedName("abilityModifier")
    @ColumnInfo(name = "strength_proficiencyBonus")
    val abilityModifier: Int,

    @SerializedName("abilityScore")
    @ColumnInfo(name = "strength_abilityScore")
    val abilityScore: Int,

    @Embedded
    @SerializedName("athletics")
    val athletics: Athletics)
