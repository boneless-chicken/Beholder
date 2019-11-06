package com.bonelesschicken.beholder.data.model.character

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @Embedded
    @SerializedName("characterInfo")
    val characterInfo: CharacterInfo,

    @Embedded
    @SerializedName("characterStats")
    val characterStats: CharacterStats,

    @ColumnInfo(name = "combatStats")
    @SerializedName("combatStats")
    val combatStats: String,

    @ColumnInfo(name = "mastery")
    @SerializedName("mastery")
    val mastery: String,

    @ColumnInfo(name = "inventory")
    @SerializedName("inventory")
    val inventory: String,

    @Embedded
    @SerializedName("characterSpells")
    val characterSpells: CharacterSpells,

    @ColumnInfo(name = "backgrounds")
    @SerializedName("backgrounds")
    val backgrounds: String)