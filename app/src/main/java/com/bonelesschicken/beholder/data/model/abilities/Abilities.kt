package com.bonelesschicken.beholder.data.model.abilities

import androidx.room.Embedded

data class Abilities(
    @Embedded
    val strength: Strength,

    @Embedded
    val dexterity: Dexterity,

    @Embedded
    val constitution: Constitution,

    @Embedded
    val intelligence: Intelligence,

    @Embedded
    val wisdom: Wisdom,

    @Embedded
    val charisma: Charisma
)
