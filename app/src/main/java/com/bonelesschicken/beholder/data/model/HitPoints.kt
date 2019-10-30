package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

class HitPoints {
    @SerializedName("totalHitPoints")
    @ColumnInfo(name = "totalHitPoints")
    var totalHitPoints: Int = 0

    @SerializedName("currentHitPoints")
    @ColumnInfo(name = "currentHitPoints")
    var currentHitPoints: Int = 0

    @Ignore
    @SerializedName("temporaryHitPoints")
    var temporaryHitPoints: List<TemporaryHitPoints> = ArrayList()
}
