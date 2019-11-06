package com.bonelesschicken.beholder.ui.character.primary


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.ui.character.CharacterViewModel
import com.bonelesschicken.beholder.ui.character.CharacterViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class PrimaryCharacterFragment : Fragment() {

    private lateinit var mTextCharacterAlignment: TextView
    private lateinit var mTextCharacterBackground: TextView
    private lateinit var mTextCharacterClass: TextView
    private lateinit var mTextCharacterExp: TextView
    private lateinit var mTextCharacterLvl: TextView
    private lateinit var mTextCharacterHp: TextView

    private lateinit var mArmorClassView: View
    private lateinit var mInitiativeViewView: View
    private lateinit var mSpeedView: View
    private lateinit var mProficiencyView: View

    private lateinit var mStrengthView: View
    private lateinit var mDexterityView: View
    private lateinit var mConstitutionView: View
    private lateinit var mIntelligenceView: View
    private lateinit var mWisdomView: View
    private lateinit var mCharismaView: View

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_primary_character, container, false)
        // Character main views
        mTextCharacterAlignment = view.findViewById(R.id.text_character_alignment)
        mTextCharacterBackground = view.findViewById(R.id.text_character_background)
        mTextCharacterClass = view.findViewById(R.id.text_character_class)
        mTextCharacterExp = view.findViewById(R.id.text_character_exp)
        mTextCharacterLvl = view.findViewById(R.id.text_character_level)
        mTextCharacterHp = view.findViewById(R.id.text_character_hp)

        // Primary scores views
        mArmorClassView = view.findViewById(R.id.card_character_armor)
        mInitiativeViewView = view.findViewById(R.id.card_character_initiative)
        mSpeedView = view.findViewById(R.id.card_character_speed)
        mProficiencyView = view.findViewById(R.id.card_character_proficiency)

        // Abilities views
        mStrengthView = view.findViewById(R.id.card_character_strength)
        mDexterityView = view.findViewById(R.id.card_character_dexterity)
        mConstitutionView = view.findViewById(R.id.card_character_constitution)
        mIntelligenceView = view.findViewById(R.id.card_character_intelligence)
        mWisdomView = view.findViewById(R.id.card_character_wisdom)
        mCharismaView = view.findViewById(R.id.card_character_charisma)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {

            characterViewModel = ViewModelProvider(this,
                CharacterViewModelFactory(it)
            )
                .get(CharacterViewModel::class.java)
            characterViewModel.getCharacter(arguments!!.getString("KEY_CHARACTER_ID")!!)
                .observe(this, Observer { character ->
                    // Main views
                    val alignment = character.characterInfo.alignment.attitude + " " + character.characterInfo.alignment.morality
                    mTextCharacterAlignment.text = alignment
                    mTextCharacterBackground.text = character.backgrounds
                    mTextCharacterClass.text = character.characterInfo.className
                    mTextCharacterExp.text = it.getString(R.string.nav_character_experience, character.characterInfo.experiencePoints.toString())
                    mTextCharacterLvl.text = it.getString(R.string.nav_character_level, character.characterInfo.level.toString())

                    mTextCharacterHp.text = it.getString(R.string.nav_character_hp,
                        character.characterStats.hitPoints.currentHitPoints.toString(),
                        character.characterStats.hitPoints.totalHitPoints.toString())

                    setPrimaryScoreViewValues(mArmorClassView,"Armor Class",
                        character.characterStats.armorClass
                    )
                    setPrimaryScoreViewValues(mInitiativeViewView,"Initiative",
                        character.characterStats.initiative
                    )
                    setPrimaryScoreViewValues(mSpeedView,"Speed", character.characterStats.speed)
                    setPrimaryScoreViewValues(mProficiencyView,"Proficiency",
                        character.characterStats.proficiencyBonus
                    )

                    // region ability views
                    setAbilityViewValues(mStrengthView,
                        "Strength",
                        character.characterStats.abilities.strength.abilityScore,
                        character.characterStats.abilities.strength.abilityModifier,
                        character.characterStats.abilities.strength.passiveCheck
                    )

                    setAbilityViewValues(mDexterityView,
                        "Dexterity",
                        character.characterStats.abilities.dexterity.abilityScore,
                        character.characterStats.abilities.dexterity.abilityModifier,
                        character.characterStats.abilities.dexterity.passiveCheck
                    )

                    setAbilityViewValues(mConstitutionView,
                        "Constitution",
                        character.characterStats.abilities.constitution.abilityScore,
                        character.characterStats.abilities.constitution.abilityModifier,
                        character.characterStats.abilities.constitution.passiveCheck
                    )

                    setAbilityViewValues(mIntelligenceView,
                        "Intelligence",
                        character.characterStats.abilities.intelligence.abilityScore,
                        character.characterStats.abilities.intelligence.abilityModifier,
                        character.characterStats.abilities.intelligence.passiveCheck
                    )

                    setAbilityViewValues(mWisdomView,
                        "Wisdom",
                        character.characterStats.abilities.wisdom.abilityScore,
                        character.characterStats.abilities.wisdom.abilityModifier,
                        character.characterStats.abilities.wisdom.passiveCheck
                    )

                    setAbilityViewValues(mCharismaView,
                        "Charisma",
                        character.characterStats.abilities.charisma.abilityScore,
                        character.characterStats.abilities.charisma.abilityModifier,
                        character.characterStats.abilities.charisma.passiveCheck
                    )
                    //endregion
                })
        }
    }

    private fun setPrimaryScoreViewValues(scoreView: View, scoreName: String, scoreValue: Int?) {
        scoreView.findViewById<TextView>(R.id.text_generic_card_title).text = scoreName
        scoreView.findViewById<TextView>(R.id.text_generic_card_value).text = scoreValue.toString()
    }

    private fun setAbilityViewValues(abilityView: View, abilityName: String, abilityScore: Int?, abilityModifier: Int?, abilitySave: Int?) {
        abilityView.findViewById<TextView>(R.id.text_character_ability_name).text = abilityName
        abilityView.findViewById<TextView>(R.id.text_character_ability_score).text = abilityScore.toString()
        abilityView.findViewById<TextView>(R.id.text_character_mod_score).text = abilityModifier.toString()
        abilityView.findViewById<TextView>(R.id.text_character_check_score).text = abilitySave.toString()
    }

}
