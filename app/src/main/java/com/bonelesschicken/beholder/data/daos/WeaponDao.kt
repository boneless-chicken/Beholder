package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.equipment.Weapon

@Dao
interface WeaponDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weapon: Weapon): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(weapon: Weapon)

    @Update
    fun updateTodo(vararg weapons: Weapon)

    @Query("SELECT * FROM weapons")
    fun getAll(): LiveData<List<Weapon>>

    @Query("SELECT * FROM weapons WHERE id = :id")
    fun getById(id: String): LiveData<Weapon>

    @Query("SELECT * FROM weapons WHERE id = :id")
    fun getByIdLocal(id: String): Weapon
}