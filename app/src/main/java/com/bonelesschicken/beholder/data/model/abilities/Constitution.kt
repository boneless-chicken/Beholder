package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Constitution(
    @SerializedName("passiveCheck")
    @ColumnInfo(name = "constitution_passiveCheck")
    val passiveCheck: Int,

    @SerializedName("abilityModifier")
    @ColumnInfo(name = "constitution_abilityModifier")
    val abilityModifier: Int,

    @SerializedName("abilityScore")
    @ColumnInfo(name = "constitution_abilityScore")
    val abilityScore: Int)
