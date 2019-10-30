package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.abilities.Abilities
import com.google.gson.annotations.SerializedName

data class CharacterStats(
    @SerializedName("id")
    @ColumnInfo(name = "characterStats_id")
    val id: String,

    @Embedded
    @SerializedName("abilities")
    val abilities: Abilities,

    @SerializedName("proficiencyBonus")
    @ColumnInfo(name = "proficiencyBonus")
    val proficiencyBonus: Int,

    @Embedded
    @SerializedName("hitPoints")
    val hitPoints: HitPoints,

    @Embedded
    @SerializedName("hitDice")
    val hitDice: HitDice,

    @SerializedName("armorClass")
    @ColumnInfo(name = "armorClass")
    val armorClass: Int,

    @SerializedName("speed")
    @ColumnInfo(name = "speed")
    val speed: Int,

    @SerializedName("initiative")
    @ColumnInfo(name = "initiative")
    val initiative: Int
)