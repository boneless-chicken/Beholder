package com.bonelesschicken.beholder.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterStatsRelation(
    @Embedded
    var characterStats: CharacterStats? = null,

    @Relation(parentColumn = "id", entityColumn = "primaryStatsId")
    var temporaryHitPoints: List<TemporaryHitPoints> = ArrayList()
)