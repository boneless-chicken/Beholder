package com.bonelesschicken.beholder.data.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.bonelesschicken.beholder.data.BeholderDatabase
import com.bonelesschicken.beholder.data.daos.CharacterDao
import com.bonelesschicken.beholder.data.daos.PrimaryStatsDao
import com.bonelesschicken.beholder.data.daos.TemporaryHitPointsDao
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.model.PrimaryStats
import com.bonelesschicken.beholder.data.model.PrimaryStatsRelation
import com.bonelesschicken.beholder.data.model.TemporaryHitPoints
import com.bonelesschicken.beholder.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(context: Context, private val apiClient: ApiClient) {
    private var db : BeholderDatabase = BeholderDatabase.invoke(context)
    private var characterDao: CharacterDao
    private var primaryStatsDao: PrimaryStatsDao
    private var temporaryHitPointsDao: TemporaryHitPointsDao

    init {
        characterDao = db.characterDao()
        primaryStatsDao = db.primaryStatsDao()
        temporaryHitPointsDao = db.temporaryHitPointsDao()
    }

    fun getCharacterList(): LiveData<List<Character>> {
        return characterDao.getAll()
    }

    fun getCharacter(characterId: String): LiveData<Character> {
        return characterDao.getById(characterId)
    }

    fun getCharacterPrimaryStats(primaryStatsId: String): LiveData<PrimaryStatsRelation> {
        apiClient.service.getPrimaryStats(primaryStatsId).enqueue(object : Callback<PrimaryStats> {
            override fun onFailure(call: Call<PrimaryStats>, t: Throwable) {

            }

            override fun onResponse(call: Call<PrimaryStats>, response: Response<PrimaryStats>) {
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
        return primaryStatsDao.getById(primaryStatsId)
    }

    private fun upsertPrimaryStats(primaryStats: PrimaryStats) {
        AsyncTask.execute {
            val id = primaryStatsDao.insert(primaryStats)
            if (id == -1L) {
                primaryStatsDao.update(primaryStats)
            }
            temporaryHitPointsDao.deleteAllByPrimaryStatsId(primaryStats.id)
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