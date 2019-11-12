package com.bonelesschicken.beholder.data.model.equipment

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bonelesschicken.beholder.data.model.Cost
import com.bonelesschicken.beholder.data.model.HitDice
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weapons")
data class Weapon(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String = "",

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String = "",

    @ColumnInfo(name = "properties")
    @SerializedName("properties")
    var properties: String = "",

    @ColumnInfo(name = "cost")
    @SerializedName("cost")
    var cost: List<Cost> = ArrayList(),

    @ColumnInfo(name = "weaponType")
    @SerializedName("weaponType")
    var weaponType: String = "",

    @ColumnInfo(name = "weaponRange")
    @SerializedName("weaponRange")
    var weaponRange: String = "",

    @Embedded
    @SerializedName("damage")
    var damage: HitDice = HitDice(0, 0),

    @ColumnInfo(name = "damageType")
    @SerializedName("damageType")
    var damageType: String = "",

    @ColumnInfo(name = "weight")
    @SerializedName("weight")
    var weight: Int = 0,

    @ColumnInfo(name = "minimumRange")
    @SerializedName("minimumRange")
    var minimumRange: Int = 0,

    @ColumnInfo(name = "maximumRange")
    @SerializedName("maximumRange")
    var maximumRange: Int = 0
)