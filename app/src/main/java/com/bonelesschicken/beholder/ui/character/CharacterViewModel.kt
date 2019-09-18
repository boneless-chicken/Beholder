package com.bonelesschicken.beholder.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.repositories.CharacterRepository
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.model.PrimaryStats

class CharacterViewModel(private val characterRepository: CharacterRepository): ViewModel() {

    fun getCharacter(characterId: String): LiveData<Character> {
        return characterRepository.getCharacter(characterId)
    }

    fun getCharacterPrimaryStats(primaryStatsId: String): LiveData<PrimaryStats> {
        return characterRepository.getCharacterPrimaryStats(primaryStatsId)
    }
}