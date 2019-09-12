package com.bonelesschicken.beholder.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.repositories.CharacterRepository
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.data.source.LoginDataSource

class MainViewModel (characterRepository: CharacterRepository) : ViewModel() {
    private val _characterList = MutableLiveData<ArrayList<Character>>()
    val characterList: LiveData<ArrayList<Character>> = _characterList

    init {
        _characterList.value = characterRepository.getCharacterList()
    }

    fun logout() {
        LoginDataSource().logout()
    }
}