package com.bonelesschicken.beholder.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bonelesschicken.beholder.data.repositories.CharacterRepository
import com.bonelesschicken.beholder.data.model.character.Character
import com.bonelesschicken.beholder.data.model.equipment.Armor
import com.bonelesschicken.beholder.data.model.equipment.Weapon
import com.bonelesschicken.beholder.data.model.spells.Spell

class CharacterViewModel(private val characterRepository: CharacterRepository): ViewModel() {
    fun getCharacter(characterId: String): LiveData<Character> {
        return characterRepository.getCharacter(characterId)
    }

    fun getSpell(id: String): Spell {
        return characterRepository.getSpell(id)
    }

    fun getWeapon(id: String): Weapon {
        return characterRepository.getWeapon(id)
    }

    fun getArmor(id: String): Armor {
        return characterRepository.getArmor(id)
    }
}