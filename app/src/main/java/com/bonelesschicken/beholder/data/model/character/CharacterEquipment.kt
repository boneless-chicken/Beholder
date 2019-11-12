package com.bonelesschicken.beholder.data.model.character

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.Cost
import com.bonelesschicken.beholder.data.model.Inventory
import com.google.gson.annotations.SerializedName

data class CharacterEquipment(
    @SerializedName("id")
    @ColumnInfo(name = "characterEquipment_id")
    var id: String = "",

    @SerializedName("armorProficiencies")
    @ColumnInfo(name = "armorProficiencies")
    var armorProficiencies: List<String> = ArrayList(),

    @SerializedName("armorEquipped")
    @ColumnInfo(name = "armorEquipped")
    var armorEquipped: List<String> = ArrayList(),

    @SerializedName("weaponProficiency")
    @ColumnInfo(name = "weaponProficiency")
    var weaponProficiency: String = "",

    @SerializedName("weaponsEquipped")
    @ColumnInfo(name = "weaponsEquipped")
    var weaponsEquipped: List<String> = ArrayList(),

    @Embedded
    @SerializedName("Inventory")
    var Inventory: Inventory = Inventory(),

    @SerializedName("wealth")
    @ColumnInfo(name = "wealth")
    var wealth: List<Cost> = ArrayList())