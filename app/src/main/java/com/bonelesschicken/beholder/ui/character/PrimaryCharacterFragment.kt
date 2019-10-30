package com.bonelesschicken.beholder.ui.character


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.R

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

            characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(it))
                .get(CharacterViewModel::class.java)
            characterViewModel.getCharacter(arguments!!.getString("KEY_CHARACTER_ID")!!)
                .observe(this, Observer { character ->
                    // Main views
                    val alignment = "Triston Mamalon"
                    mTextCharacterAlignment.text = alignment
                    mTextCharacterBackground.text = "Soldado del amor"
                    mTextCharacterClass.text = "ANIMAL"
                    mTextCharacterExp.text = it.getString(R.string.nav_character_experience, "13")
                    mTextCharacterLvl.text = it.getString(R.string.nav_character_level, "14")

                    characterViewModel.getCharacterPrimaryStats(character.characterStats)
                        .observe(this, Observer { relation ->
                            if (relation != null) {
                                // Set views with primary information
                                mTextCharacterHp.text = it.getString(R.string.nav_character_hp,
                                    relation.characterStats?.hitPoints?.currentHitPoints.toString(),
                                    relation.characterStats?.hitPoints?.totalHitPoints.toString())

                                setPrimaryScoreViewValues(mArmorClassView,"Armor Class", relation.characterStats?.armorClass)
                                setPrimaryScoreViewValues(mInitiativeViewView,"Initiative", relation.characterStats?.initiative)
                                setPrimaryScoreViewValues(mSpeedView,"Speed", relation.characterStats?.speed)
                                setPrimaryScoreViewValues(mProficiencyView,"Proficiency", relation.characterStats?.proficiencyBonus)

                                // region ability views
                                setAbilityViewValues(mStrengthView,
                                    "Strength",
                                    relation.characterStats?.abilities?.strength?.abilityScore,
                                    relation.characterStats?.abilities?.strength?.abilityModifier,
                                    relation.characterStats?.abilities?.strength?.passiveCheck)

                                setAbilityViewValues(mDexterityView,
                                    "Dexterity",
                                    relation.characterStats?.abilities?.dexterity?.abilityScore,
                                    relation.characterStats?.abilities?.dexterity?.abilityModifier,
                                    relation.characterStats?.abilities?.dexterity?.passiveCheck)

                                setAbilityViewValues(mConstitutionView,
                                    "Constitution",
                                    relation.characterStats?.abilities?.constitution?.abilityScore,
                                    relation.characterStats?.abilities?.constitution?.abilityModifier,
                                    relation.characterStats?.abilities?.constitution?.passiveCheck)

                                setAbilityViewValues(mIntelligenceView,
                                    "Intelligence",
                                    relation.characterStats?.abilities?.intelligence?.abilityScore,
                                    relation.characterStats?.abilities?.intelligence?.abilityModifier,
                                    relation.characterStats?.abilities?.intelligence?.passiveCheck)

                                setAbilityViewValues(mWisdomView,
                                    "Wisdom",
                                    relation.characterStats?.abilities?.wisdom?.abilityScore,
                                    relation.characterStats?.abilities?.wisdom?.abilityModifier,
                                    relation.characterStats?.abilities?.wisdom?.passiveCheck)

                                setAbilityViewValues(mCharismaView,
                                    "Charisma",
                                    relation.characterStats?.abilities?.charisma?.abilityScore,
                                    relation.characterStats?.abilities?.charisma?.abilityModifier,
                                    relation.characterStats?.abilities?.charisma?.passiveCheck)
                                //endregion
                            }
                        })
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
