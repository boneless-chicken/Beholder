package com.bonelesschicken.beholder.network

import com.bonelesschicken.beholder.data.model.PrimaryStats
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.network.responses.GetCharactersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpellbinderService {

    @GET("/Characters/{uid}")
    fun getCharacters(@Path("uid") uid: String): Call<GetCharactersResponse>

    @GET("/Characters/PrimaryStats/{id}")
    fun getPrimaryStats(@Path("id") id: String): Call<PrimaryStats>

    @POST("/CreateUser")
    fun createUser(@Body user: User): Call<User>
}