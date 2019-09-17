package com.bonelesschicken.beholder.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.repositories.CharacterRepository
import com.bonelesschicken.beholder.data.model.Character

class CharacterViewModel(private val characterRepository: CharacterRepository): ViewModel() {

    fun getCharacterDetail(characterId: String): LiveData<Character> {
        return characterRepository.getCharacterDetails(characterId)
    }
}