package com.bonelesschicken.beholder.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.data.repositories.CharacterRepository
import com.bonelesschicken.beholder.network.ApiClient

class MainViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                characterRepository = CharacterRepository(
                    apiClient = ApiClient()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}