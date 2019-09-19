package com.bonelesschicken.beholder.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bonelesschicken.beholder.data.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(characters: Character): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(character: Character)

    @Update
    fun updateTodo(vararg characters: Character)

    @Query("SELECT * FROM characters")
    fun getAll(): LiveData<List<Character>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getById(id: String): LiveData<Character>
}