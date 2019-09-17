package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.network.ApiClient
import com.bonelesschicken.beholder.network.responses.GetCharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(context: Context, private val apiClient: ApiClient) {
    private var db : BeholderDatabase = BeholderDatabase.invoke(context)
    private var characterDao: CharacterDao

    init {
        characterDao = db.characterDao()
    }

    fun getCharacterList(): LiveData<List<Character>> {
        return characterDao.getAll()
    }

    fun getCharacterDetails(characterId: String): LiveData<Character> {
        return characterDao.getById(characterId)
    }
}