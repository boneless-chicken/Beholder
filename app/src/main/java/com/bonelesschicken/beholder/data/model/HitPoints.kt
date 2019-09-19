package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo
import androidx.room.Ignore

class HitPoints {
    @ColumnInfo(name = "totalHitPoints")
    var totalHitPoints: Int = 0

    @ColumnInfo(name = "currentHitPoints")
    var currentHitPoints: Int = 0

    @Ignore
    var temporaryHitPoints: List<TemporaryHitPoints> = ArrayList()
}
