package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bonelesschicken.beholder.data.model.abilities.Abilities

@Entity(tableName = "character_stats")
data class CharacterStats(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @Embedded
    val abilities: Abilities,

    @ColumnInfo(name = "proficiencyBonus")
    val proficiencyBonus: Int,

    @Embedded
    val hitPoints: HitPoints,

    @Embedded
    val hitDice: HitDice,

    @ColumnInfo(name = "armorClass")
    val armorClass: Int,

    @ColumnInfo(name = "speed")
    val speed: Int,

    @ColumnInfo(name = "initiative")
    val initiative: Int
)