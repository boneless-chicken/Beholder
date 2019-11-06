package com.bonelesschicken.beholder.data.model.spells

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class SpellDamage(
    @ColumnInfo(name = "damageType")
    @SerializedName("damageType")
    var damageType: String = "",

    @ColumnInfo(name = "scalingDamage")
    @SerializedName("scalingDamage")
    var scalingDamage: List<ScalingDamage> = ArrayList())
