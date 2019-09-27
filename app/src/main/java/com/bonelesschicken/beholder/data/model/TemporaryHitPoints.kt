package com.bonelesschicken.beholder.data.model

import androidx.room.*

@Entity(tableName = "temporary_hit_points",
    indices = [Index("primaryStatsId")],
    foreignKeys = [ForeignKey(entity = CharacterStats::class,
        parentColumns = ["id"],
        childColumns = ["primaryStatsId"],
        onDelete = ForeignKey.CASCADE
    )])
data class TemporaryHitPoints(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "source")
    val source: String,

    @ColumnInfo(name = "point")
    val point: Int,

    @ColumnInfo(name = "duration")
    val duration: Int,

    @ColumnInfo(name = "primaryStatsId")
    var primaryStatsId: String? = null
)
