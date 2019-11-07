package com.bonelesschicken.beholder.data.model.character

import androidx.room.ColumnInfo
import androidx.room.Ignore
import com.bonelesschicken.beholder.data.model.spells.SpellSlot
import com.google.gson.annotations.SerializedName

data class CharacterSpells(
    @SerializedName("id")
    @ColumnInfo(name = "characterSpells_id")
    var id: String = "",

    @SerializedName("spellSlots")
    @ColumnInfo(name = "spellSlots")
    var spellSlots: List<SpellSlot> = ArrayList(),

    @SerializedName("concentration")
    @ColumnInfo(name = "concentration")
    var concentration: Int = 0,

    @SerializedName("spellcastingModifier")
    @ColumnInfo(name = "spellcastingModifier")
    var spellcastingModifier: Int = 0,

    @SerializedName("spellAttackBonus")
    @ColumnInfo(name = "spellAttackBonus")
    var spellAttackBonus: Int = 0,

    @SerializedName("maxSpellsPrepared")
    @ColumnInfo(name = "maxSpellsPrepared")
    var maxSpellsPrepared: String = "",

    @SerializedName("spellsPrepared")
    var spellsPrepared: List<String> = ArrayList(),

    @SerializedName("spellsKnown")
    var spellsKnown: List<String> = ArrayList()

)