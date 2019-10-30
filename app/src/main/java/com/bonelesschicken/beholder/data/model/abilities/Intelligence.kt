package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.bonelesschicken.beholder.data.model.skills.*
import com.google.gson.annotations.SerializedName

data class Intelligence(
    @SerializedName("passiveCheck")
    @ColumnInfo(name = "intelligence_passiveCheck")
    val passiveCheck: Int,

    @SerializedName("abilityModifier")
    @ColumnInfo(name = "intelligence_abilityModifier")
    val abilityModifier: Int,

    @SerializedName("abilityScore")
    @ColumnInfo(name = "intelligence_abilityScore")
    val abilityScore: Int,

    @Embedded
    @SerializedName("arcana")
    val arcana: Arcana,

    @Embedded
    @SerializedName("history")
    val history: History,

    @Embedded
    @SerializedName("investigation")
    val investigation: Investigation,

    @Embedded
    @SerializedName("nature")
    val nature: Nature,

    @Embedded
    @SerializedName("religion")
    val religion: Religion
)
