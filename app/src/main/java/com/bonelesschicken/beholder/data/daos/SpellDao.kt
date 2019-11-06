package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.spells.Spell

@Dao
interface SpellDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(spell: Spell): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(spell: Spell)

    @Update
    fun updateTodo(vararg spells: Spell)

    @Query("SELECT * FROM spells")
    fun getAll(): LiveData<List<Spell>>

    @Query("SELECT * FROM spells WHERE id = :id")
    fun getById(id: String): LiveData<Spell>

    @Query("SELECT * FROM spells WHERE id = :id")
    fun getByIdLocal(id: String): Spell
}