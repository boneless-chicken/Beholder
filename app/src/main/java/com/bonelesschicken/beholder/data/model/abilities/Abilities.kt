package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Abilities(
    @Embedded
    @SerializedName("strength")
    val strength: Strength,

    @Embedded
    @SerializedName("dexterity")
    val dexterity: Dexterity,

    @Embedded
    @SerializedName("constitution")
    val constitution: Constitution,

    @Embedded
    @SerializedName("intelligence")
    val intelligence: Intelligence,

    @Embedded
    @SerializedName("wisdom")
    val wisdom: Wisdom,

    @Embedded
    @SerializedName("charisma")
    val charisma: Charisma
)
