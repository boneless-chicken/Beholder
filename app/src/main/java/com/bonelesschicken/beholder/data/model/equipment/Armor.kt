package com.bonelesschicken.beholder.data.model.equipment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bonelesschicken.beholder.data.model.Cost
import com.google.gson.annotations.SerializedName

@Entity(tableName = "armors")
data class Armor(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String = "",

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String = "",

    @ColumnInfo(name = "cost")
    @SerializedName("cost")
    var cost: List<Cost> = ArrayList(),

    @ColumnInfo(name = "armorType")
    @SerializedName("armorType")
    var armorType: String = "",

    @ColumnInfo(name = "strengthNeeded")
    @SerializedName("strengthNeeded")
    var strengthNeeded: Int = 0,

    @ColumnInfo(name = "dexterityDisadvantage")
    @SerializedName("dexterityDisadvantage")
    var dexterityDisadvantage: Boolean = false,

    @ColumnInfo(name = "weight")
    @SerializedName("weight")
    var weight: Int = 0,

    @ColumnInfo(name = "armorClass")
    @SerializedName("armorClass")
    var armorClass: Int = 0
)