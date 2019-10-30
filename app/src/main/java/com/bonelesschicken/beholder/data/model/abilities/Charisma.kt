package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.Deception
import com.bonelesschicken.beholder.data.model.skills.Intimidation
import com.bonelesschicken.beholder.data.model.skills.Performance
import com.bonelesschicken.beholder.data.model.skills.Persuasion
import com.google.gson.annotations.SerializedName

data class Charisma(
    @SerializedName("passiveCheck")
    @ColumnInfo(name = "charisma_passiveCheck")
    val passiveCheck: Int,

    @SerializedName("abilityModifier")
    @ColumnInfo(name = "charisma_abilityModifier")
    val abilityModifier: Int,

    @SerializedName("abilityScore")
    @ColumnInfo(name = "charisma_abilityScore")
    val abilityScore: Int,

    @Embedded
    @SerializedName("deception")
    val deception: Deception,

    @Embedded
    @SerializedName("intimidation")
    val intimidation: Intimidation,

    @Embedded
    @SerializedName("performance")
    val performance: Performance,

    @Embedded
    @SerializedName("persuasion")
    val persuasion: Persuasion)
