package com.bonelesschicken.beholder.data.model

data class Character(val id: Long,
                     val name: String,
                     val classes: List<CharacterClass>,
                     val race: Race,
                     val alignment: Alignment,
                     val personalityTraits: String,
                     val ideals: String,
                     val bonds: String,
                     val flaws: String,
                     val background: Background,
                     val level: Int,
                     val experience: Int,
                     val armorClass: Int,
                     val initiative: Int,
                     val speed: Int,
                     val proficiencyBonus: Int,
                     val inspirationPoints: Int,
                     val hitPoints: Int,
                     val currentHitPoints: Int,
                     val inventory: List<Item>,
                     val otherProficiencies: List<String>,
                     val languages: List<Language>,
                     val featuresAndTraits: List<String>,
                     val spellSaveDC: Int,
                     val spellSlots: Int,
                     val spellAttackBonus: Int)