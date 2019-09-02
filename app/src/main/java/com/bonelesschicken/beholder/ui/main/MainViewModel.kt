package com.bonelesschicken.beholder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.CharacterRepository
import com.bonelesschicken.beholder.data.model.Character

class MainViewModel (characterRepository: CharacterRepository) : ViewModel() {
    private val _characterList = MutableLiveData<ArrayList<Character>>()
    val characterList: LiveData<ArrayList<Character>> = _characterList

    init {
        _characterList.value = characterRepository.getCharacterList()
    }
}