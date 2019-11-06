package com.bonelesschicken.beholder.data.model.spells

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "spells")
data class Spell(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @ColumnInfo(name = "requiredLevel")
    @SerializedName("requiredLevel")
    val requiredLevel: Int,

    @ColumnInfo(name = "spellType")
    @SerializedName("spellType")
    val spellType: String,

    @Embedded
    @SerializedName("spellCasting")
    val spellCasting: SpellCasting,

    @Embedded
    @SerializedName("spellRange")
    val spellRange: SpellRange,

    @Embedded
    @SerializedName("spellDuration")
    val spellDuration: SpellDuration,

    @Embedded
    @SerializedName("spellComponents")
    val spellComponents: SpellComponents,

    @Embedded
    @SerializedName("spellDamage")
    val spellDamage: SpellDamage,

    @ColumnInfo(name = "schoolOfMagic")
    @SerializedName("schoolOfMagic")
    val schoolOfMagic: String,

    @ColumnInfo(name = "savingThrows")
    @SerializedName("savingThrows")
    val savingThrows: String,

    @ColumnInfo(name = "atHigherLevels")
    @SerializedName("atHigherLevels")
    val atHigherLevels: String)