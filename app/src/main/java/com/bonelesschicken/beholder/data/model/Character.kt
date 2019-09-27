package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "characterClass")
    @SerializedName("characterInfo")
    val characterInfo: String,

    @ColumnInfo(name = "characterStats")
    @SerializedName("characterStats")
    val characterStats: String,

    @ColumnInfo(name = "combatStats")
    @SerializedName("combatStats")
    val combatStats: String,

    @ColumnInfo(name = "mastery")
    @SerializedName("mastery")
    val mastery: String,

    @ColumnInfo(name = "inventory")
    @SerializedName("inventory")
    val inventory: String,

    @ColumnInfo(name = "characterSpells")
    @SerializedName("characterSpells")
    val characterSpells: String,

    @ColumnInfo(name = "backgrounds")
    @SerializedName("backgrounds")
    val backgrounds: String)