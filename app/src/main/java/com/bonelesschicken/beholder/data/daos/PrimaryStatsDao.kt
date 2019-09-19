package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.PrimaryStats

@Dao
interface PrimaryStatsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(primaryStats: PrimaryStats): Long

    @Query("SELECT * FROM primary_stats WHERE id = :id")
    fun getById(id: String): LiveData<PrimaryStats>

    @Delete
    fun delete(primaryStats: PrimaryStats)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(primaryStats: PrimaryStats)
}