package com.bonelesschicken.beholder.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.CharacterRepository
import com.bonelesschicken.beholder.data.model.Character

class CharacterViewModel(private val characterRepository: CharacterRepository): ViewModel() {
    private val _characterDetail = MutableLiveData<Character>()

    fun getCharacterDetail(characterId: Long): LiveData<Character> {
        _characterDetail.value = characterRepository.getCharacterDetails(characterId)
        return _characterDetail
    }
}