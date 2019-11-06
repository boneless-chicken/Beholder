package com.bonelesschicken.beholder.network

import com.bonelesschicken.beholder.data.model.character.Character
import com.bonelesschicken.beholder.network.responses.GameData
import com.bonelesschicken.beholder.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpellbinderService {

    @GET("/GameData")
    fun getGameData(): Call<GameData>

    @POST("/User/Register")
    fun createUser(@Body user: User): Call<User>

    @GET("/User/{uid}/Characters")
    fun getCharactersData(@Path("uid") uid: String): Call<List<Character>>
}