package com.bonelesschicken.beholder.network.responses

import com.bonelesschicken.beholder.data.model.equipment.Armor
import com.bonelesschicken.beholder.data.model.equipment.Weapon
import com.bonelesschicken.beholder.data.model.spells.Spell

data class GameData(
    val spells: ArrayList<Spell>,
    val armors: ArrayList<Armor>,
    val weapons: ArrayList<Weapon>
)