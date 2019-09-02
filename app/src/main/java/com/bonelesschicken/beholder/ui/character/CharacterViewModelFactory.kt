package com.bonelesschicken.beholder.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.data.CharacterRepository
import com.bonelesschicken.beholder.network.ApiClient

class CharacterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(
                characterRepository = CharacterRepository(
                    apiClient = ApiClient()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
