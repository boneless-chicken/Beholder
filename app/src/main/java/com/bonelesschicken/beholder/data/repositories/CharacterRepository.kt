package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.daos.PrimaryStatsDao
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.model.PrimaryStats
import com.bonelesschicken.beholder.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(context: Context, private val apiClient: ApiClient) {
    private var db : BeholderDatabase = BeholderDatabase.invoke(context)
    private var characterDao: CharacterDao
    private var primaryStatsDao: PrimaryStatsDao

    init {
        characterDao = db.characterDao()
        primaryStatsDao = db.primaryStatsDao()
    }

    fun getCharacterList(): LiveData<List<Character>> {
        return characterDao.getAll()
    }

    fun getCharacter(characterId: String): LiveData<Character> {
        return characterDao.getById(characterId)
    }

    fun getCharacterPrimaryStats(primaryStatsId: String): LiveData<PrimaryStats> {
        apiClient.service.getPrimaryStats(primaryStatsId).enqueue(object : Callback<PrimaryStats> {
            override fun onFailure(call: Call<PrimaryStats>, t: Throwable) {

            }

            override fun onResponse(call: Call<PrimaryStats>, response: Response<PrimaryStats>) {
                if (response.isSuccessful && response.body() != null) {
                    val primaryStats = response.body()
                    if (primaryStats != null) {
                        insertPrimaryStats(primaryStats)
                    }
                }
            }

        })
        return primaryStatsDao.getById(primaryStatsId)
    }

    private fun insertPrimaryStats(primaryStats: PrimaryStats) {
        AsyncTask.execute {
            primaryStatsDao.insertAll(primaryStats)
        }
    }
}