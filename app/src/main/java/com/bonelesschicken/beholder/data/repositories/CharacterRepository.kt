package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.daos.CharacterStatsDao
import com.bonelesschicken.beholder.data.daos.TemporaryHitPointsDao
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.model.CharacterStats
import com.bonelesschicken.beholder.data.model.CharacterStatsRelation
import com.bonelesschicken.beholder.data.model.TemporaryHitPoints
import com.bonelesschicken.beholder.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(context: Context, private val apiClient: ApiClient) {
    private var db : BeholderDatabase = BeholderDatabase.invoke(context)
    private var characterDao: CharacterDao
    private var characterStatsDao: CharacterStatsDao
    private var temporaryHitPointsDao: TemporaryHitPointsDao

    init {
        characterDao = db.characterDao()
        characterStatsDao = db.characterStatsDao()
        temporaryHitPointsDao = db.temporaryHitPointsDao()
    }

    fun getCharacterList(): LiveData<List<Character>> {
        return characterDao.getAll()
    }

    fun getCharacter(characterId: String): LiveData<Character> {
        return characterDao.getById(characterId)
    }

    fun getCharacterPrimaryStats(primaryStatsId: String): LiveData<CharacterStatsRelation> {
        apiClient.service.getPrimaryStats(primaryStatsId).enqueue(object : Callback<CharacterStats> {
            override fun onFailure(call: Call<CharacterStats>, t: Throwable) {

            }

            override fun onResponse(call: Call<CharacterStats>, response: Response<CharacterStats>) {
                if (response.isSuccessful && response.body() != null) {
                    val primaryStats = response.body()
                    if (primaryStats != null) {
                        upsertPrimaryStats(primaryStats)
                        primaryStats.hitPoints.temporaryHitPoints.forEach {
                            it.primaryStatsId = primaryStats.id
                            upsertTemporaryHitPoints(it)
                        }
                    }
                }
            }

        })
        return characterStatsDao.getById(primaryStatsId)
    }

    private fun upsertPrimaryStats(characterStats: CharacterStats) {
        AsyncTask.execute {
            val id = characterStatsDao.insert(characterStats)
            if (id == -1L) {
                characterStatsDao.update(characterStats)
            }
            temporaryHitPointsDao.deleteAllByPrimaryStatsId(characterStats.id)
        }
    }

    private fun upsertTemporaryHitPoints(temporaryHitPoints: TemporaryHitPoints) {
        AsyncTask.execute {
            val id = temporaryHitPointsDao.insert(temporaryHitPoints)
            if (id == -1L) {
                temporaryHitPointsDao.update(temporaryHitPoints)
            }
        }
    }
}