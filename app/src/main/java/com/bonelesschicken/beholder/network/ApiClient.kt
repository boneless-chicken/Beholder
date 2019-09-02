package com.bonelesschicken.beholder.network

import com.bonelesschicken.beholder.data.model.Character

class ApiClient {
    fun getCharacterList(): ArrayList<Character> {
        return arrayListOf(
            Character("Trombadin", "Eneano", 14),
            Character("Gandalf", "Mago", 12),
            Character("Sauron", "Malo malote", 666),
            Character("Frodo", "Hobbit", 2),
            Character("Sneaky", "Trapito", 69),
            Character("Chango", "Primate", 0),
            Character("Legonas", "Elfo", 89),
            Character("El Emperador", "Ruco", 66),
            Character("Fernando", "Maricon", -1),
            Character("Brian", "Diabetin", 76)
        )
    }
}