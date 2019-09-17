package com.bonelesschicken.beholder.data.model

import androidx.room.ColumnInfo

data class Alignment(
    @ColumnInfo(name = "attitude")
    val attitude: String,

    @ColumnInfo(name = "morality")
    val morality: String)