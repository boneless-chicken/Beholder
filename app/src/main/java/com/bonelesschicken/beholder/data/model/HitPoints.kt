package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class HitPoints {
    @SerializedName("totalHitPoints")
    @ColumnInfo(name = "totalHitPoints")
    var totalHitPoints: Int = 0

    @SerializedName("currentHitPoints")
    @ColumnInfo(name = "currentHitPoints")
    var currentHitPoints: Int = 0

    @SerializedName("temporaryHitPoints")
    @ColumnInfo(name = "temporaryHitPoints")
    var temporaryHitPoints: List<TemporaryHitPoints> = ArrayList()
}
