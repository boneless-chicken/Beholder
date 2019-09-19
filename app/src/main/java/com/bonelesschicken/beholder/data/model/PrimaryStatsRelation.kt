package com.bonelesschicken.beholder.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class PrimaryStatsRelation(
    @Embedded
    var primaryStats: PrimaryStats? = null,

    @Relation(parentColumn = "id", entityColumn = "primaryStatsId")
    var temporaryHitPoints: List<TemporaryHitPoints> = ArrayList()
)