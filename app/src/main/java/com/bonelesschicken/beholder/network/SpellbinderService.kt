package com.bonelesschicken.beholder.network

import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.network.responses.GetCharactersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpellbinderService {

    @GET("/characters/{uid}")
    fun getCharacters(@Path("uid") uid: String): Call<GetCharactersResponse>

    @POST("/CreateUser")
    fun createUser(@Body user: User): Call<User>
}