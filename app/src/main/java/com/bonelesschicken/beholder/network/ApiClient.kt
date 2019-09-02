package com.bonelesschicken.beholder.network

import com.bonelesschicken.beholder.data.model.Character

class ApiClient {
    private val characters = arrayListOf(
        Character(1L, "Trombadin", "Eneano", 14),
        Character(2L, "Gandalf", "Mago", 12),
        Character(3L, "Sauron", "Malo malote", 666),
        Character(4L, "Frodo", "Hobbit", 2),
        Character(5L, "Sneaky", "Trapito", 69),
        Character(6L, "Chango", "Primate", 0),
        Character(7L, "Legonas", "Elfo", 89),
        Character(8L, "El Emperador", "Ruco", 66),
        Character(9L, "Fernando", "Maricon", -1),
        Character(10L,"Brian", "Diabetin", 76)
    )

    fun getCharacterList(): ArrayList<Character> {
        return characters
    }

    fun getCharacterDetail(characterId: Long): Character? {
        return characters.firstOrNull { it.id == characterId }
    }
}