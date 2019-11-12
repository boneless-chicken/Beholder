package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.equipment.Armor

@Dao
interface ArmorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(armor: Armor): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(armor: Armor)

    @Update
    fun updateTodo(vararg armors: Armor)

    @Query("SELECT * FROM armors")
    fun getAll(): LiveData<List<Armor>>

    @Query("SELECT * FROM armors WHERE id = :id")
    fun getById(id: String): LiveData<Armor>

    @Query("SELECT * FROM armors WHERE id = :id")
    fun getByIdLocal(id: String): Armor
}