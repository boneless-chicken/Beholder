package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.PrimaryStats

@Dao
interface PrimaryStatsDao {
    @Insert
    fun insertAll(vararg primaryStats: PrimaryStats)

    @Query("SELECT * FROM primary_stats WHERE id = :id")
    fun getById(id: String): LiveData<PrimaryStats>

    @Delete
    fun delete(primaryStats: PrimaryStats)

    @Update
    fun updateTodo(vararg primaryStats: PrimaryStats)
}