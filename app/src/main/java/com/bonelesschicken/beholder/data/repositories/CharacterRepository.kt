package com.bonelesschicken.beholder.data.repositories

import androidx.lifecycle.MutableLiveData
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.network.ApiClient
import com.bonelesschicken.beholder.network.responses.GetCharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(private val apiClient: ApiClient) {

    fun getCharacterList(): ArrayList<Character> {
        return arrayListOf()
    }

    fun getCharacterList(uid: String, model: MutableLiveData<ArrayList<Character>>) {
        apiClient.service.getCharacters(uid).enqueue(object : Callback<GetCharactersResponse> {
            override fun onFailure(call: Call<GetCharactersResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<GetCharactersResponse>,
                response: Response<GetCharactersResponse>
            ) {
                if (response.isSuccessful) {
                    model.value = response.body()?.characters
                }
            }
        })
    }

    fun getCharacterDetails(characterId: Long): Character? {
        return null
    }
}