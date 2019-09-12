package com.bonelesschicken.beholder.data.repositories

import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.network.ApiClient

class CharacterRepository(private val apiClient: ApiClient) {

    fun getCharacterList(): ArrayList<Character> {
        return apiClient.getCharacterList()
    }

    fun getCharacterDetails(characterId: Long): Character? {
        return apiClient.getCharacterDetail(characterId)
    }
}