package com.bonelesschicken.beholder.data.model

import com.google.gson.annotations.SerializedName

data class TemporaryHitPoints(
    @SerializedName("source")
    val source: String,
    @SerializedName("point")
    val point: Int,
    @SerializedName("duration")
    val duration: Int
)
