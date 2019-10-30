package com.bonelesschicken.beholder.data.model

data class TemporaryHitPoints(
    val id: Long,

    val source: String,

    val point: Int,

    val duration: Int,

    var primaryStatsId: String? = null
)
