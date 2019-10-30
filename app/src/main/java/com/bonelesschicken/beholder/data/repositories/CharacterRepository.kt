package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(context: Context, private val apiClient: ApiClient) {
    private var db : BeholderDatabase = BeholderDatabase.invoke(context)
    private var characterDao: CharacterDao

    init {
        characterDao = db.characterDao()
    }

    fun getCharacterList(uid: String): LiveData<List<Character>> {
        apiClient.service.getCharactersData(uid)
            .enqueue(object : Callback<List<Character>> {
                override fun onResponse(call: Call<List<Character>>,
                                        response: Response<List<Character>>) {
                    if (response.isSuccessful) {
                        AsyncTask.execute {
                            response.body()?.forEach {
                                upsertCharacter(it)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<Character>>, t: Throwable) {

                }
            })

        return characterDao.getAll()
    }

    private fun upsertCharacter(character: Character) {
        val id = characterDao.insert(character)
        if (id == -1L) {
            characterDao.update(character)
        }
    }

    fun getCharacter(characterId: String): LiveData<Character> {
        return characterDao.getById(characterId)
    }
}