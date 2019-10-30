package com.bonelesschicken.beholder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.repositories.CharacterRepository

class MainViewModel (val characterRepository: CharacterRepository) : ViewModel() {

    fun getCharacters(uid: String): LiveData<List<Character>> {
        return characterRepository.getCharacterList(uid)
    }
}