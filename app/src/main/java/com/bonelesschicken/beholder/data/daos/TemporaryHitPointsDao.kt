package com.bonelesschicken.beholder.data.daos

import androidx.room.*
import com.bonelesschicken.beholder.data.model.TemporaryHitPoints

@Dao
interface TemporaryHitPointsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(temporaryHitPoints: TemporaryHitPoints): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg temporaryHitPoints: TemporaryHitPoints)

    @Query("DELETE FROM temporary_hit_points WHERE primaryStatsId = :primaryStatsId")
    fun deleteAllByPrimaryStatsId(primaryStatsId: String)
}