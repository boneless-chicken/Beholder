package com.bonelesschicken.beholder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.repositories.CharacterRepository

class MainViewModel (val characterRepository: CharacterRepository) : ViewModel() {
    private val _characterList = MutableLiveData<ArrayList<Character>>()
    val characterList: LiveData<ArrayList<Character>> = _characterList

    init {
        _characterList.value = characterRepository.getCharacterList()
    }

    fun getCharacters(uid: String) {
        characterRepository.getCharacterList(uid, _characterList)
    }
}