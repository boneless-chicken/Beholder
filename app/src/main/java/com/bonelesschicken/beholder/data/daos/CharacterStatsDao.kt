package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.CharacterStats
import com.bonelesschicken.beholder.data.model.CharacterStatsRelation

@Dao
interface CharacterStatsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(characterStats: CharacterStats): Long

    @Transaction
    @Query("SELECT * FROM character_stats WHERE id = :id")
    fun getById(id: String): LiveData<CharacterStatsRelation>

    @Delete
    fun delete(characterStats: CharacterStats)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(characterStats: CharacterStats)
}