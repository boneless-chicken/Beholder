package com.bonelesschicken.beholder.network

import com.bonelesschicken.beholder.data.model.*

class ApiClient {
    private val characters = arrayListOf(
        Character(1L,
            "Trombadin",
            arrayListOf(
                CharacterClass(
                    name = "Cleric",
                    description = "Description",
                    hitDie = Dice(),
                    primaryAbility = Ability(
                        name = "Strength",
                        score = 10,
                        description = "Description",
                        skills = arrayListOf(
                            Skill(),
                            Skill()
                        )
                    ),
                    proficiencyBonus = 2,
                    features = arrayListOf(
                        Feature(),
                        Feature()
                    ),
                    skills = arrayListOf(
                        Skill(),
                        Skill()
                    ),
                    savingThrows = arrayListOf(
                        Ability(
                            name = "Strength",
                            score = 10,
                            description = "Description",
                            skills = arrayListOf(
                                Skill(),
                                Skill()
                            )
                        ),
                        Ability(
                            "Dextery",
                            10,
                            "Description",
                            arrayListOf(
                                Skill(),
                                Skill()
                            )
                        )
                    ),
                    tools = arrayListOf(
                        Tool(),
                        Tool()
                    ),
                    armorProficiencies = arrayListOf(
                        Armor(),
                        Armor()
                    ),
                    weaponProficiencies = arrayListOf(
                        Weapon(),
                        Weapon()
                    ),
                    cantripsKnown = 2,
                    spellsKnow = 4,
                    spellSaveDC = 13,
                    spellAttackModifier = 5,
                    martialArts = 0,
                    kiPoints = 0,
                    unarmoredMovement = 0
                )),
            abilities = arrayListOf(),
            race = Race(),
            alignment = Alignment(),
            personalityTraits = "Personality traits",
            ideals = "Ideals",
            bonds = "Bonds",
            flaws = "Flaws",
            background = Background(
                name = "Criminal",
                feature = BackgroundFeature("BackgroundFeature", "backgroundDescription"),
                proficiencies = arrayListOf(
                    Skill(),
                    Skill()
                )
            ),
            level = 12,
            experience = 333,
            armorClass = 15,
            initiative = 13,
            speed = 50,
            proficiencyBonus = 2,
            inspirationPoints = 0,
            hitPoints = 30,
            currentHitPoints = 30,
            inventory = arrayListOf(
                Item(),
                Item()
            ),
            otherProficiencies = arrayListOf(
                "",
                "",
                ""
            ),
            languages = arrayListOf(
                Language(),
                Language()
            ),
            featuresAndTraits = arrayListOf(
                "",
                "",
                ""
            ),
            spellSaveDC = 15,
            spellSlots = 12,
            spellAttackBonus = 2
        )
    )

    fun getCharacterList(): ArrayList<Character> {
        return characters
    }

    fun getCharacterDetail(characterId: Long): Character? {
        return characters.firstOrNull { it.id == characterId }
    }
}