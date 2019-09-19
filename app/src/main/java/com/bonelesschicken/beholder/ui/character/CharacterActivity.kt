package com.bonelesschicken.beholder.ui.character

import android.os.Bundle
import android.telephony.CellSignalStrength
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.ui.BaseActivity
import com.bonelesschicken.beholder.utils.BottomAppBarCutCornersTopEdge
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable

class CharacterActivity : BaseActivity() {

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
    }

    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavigationHeader: View

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mBottomAppBar: BottomAppBar
    private lateinit var mFabButton: FloatingActionButton

    private lateinit var mTextCharacterName: TextView
    private lateinit var mTextCharacterRace: TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        mBottomAppBar = findViewById(R.id.bottom_bar_character)
        mFabButton = findViewById(R.id.fab_button_character)

        val topEdge = BottomAppBarCutCornersTopEdge(
            mBottomAppBar.fabCradleMargin,
            mBottomAppBar.fabCradleRoundedCornerRadius,
            mBottomAppBar.cradleVerticalOffset
        )
        val babBackground = mBottomAppBar.background as MaterialShapeDrawable
        babBackground.shapeAppearanceModel = babBackground.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()
        babBackground.invalidateSelf()

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, mBottomAppBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById(R.id.nav_view)
        mNavigationHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_character)

        // Character main views
        mTextCharacterName = findViewById(R.id.text_character_name)
        mTextCharacterRace = findViewById(R.id.text_character_race)
        mTextCharacterAlignment = findViewById(R.id.text_character_alignment)
        mTextCharacterBackground = findViewById(R.id.text_character_background)
        mTextCharacterClass = findViewById(R.id.text_character_class)
        mTextCharacterExp = findViewById(R.id.text_character_exp)
        mTextCharacterLvl = findViewById(R.id.text_character_level)
        mTextCharacterHp = findViewById(R.id.text_character_hp)

        // Primary scores views
        mArmorClassView = findViewById(R.id.card_character_armor)
        mInitiativeViewView = findViewById(R.id.card_character_initiative)
        mSpeedView = findViewById(R.id.card_character_speed)
        mProficiencyView = findViewById(R.id.card_character_proficiency)

        // Abilities views
        mStrengthView = findViewById(R.id.card_character_strength)
        mDexterityView = findViewById(R.id.card_character_dexterity)
        mConstitutionView = findViewById(R.id.card_character_constitution)
        mIntelligenceView = findViewById(R.id.card_character_intelligence)
        mWisdomView = findViewById(R.id.card_character_wisdom)
        mCharismaView = findViewById(R.id.card_character_charisma)

        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(this)).get(CharacterViewModel::class.java)

        if (intent.hasExtra(CHARACTER_ID)) {
            characterViewModel.getCharacter(intent.getStringExtra(CHARACTER_ID))
                .observe(this, Observer { character ->
                    // Main views
                    mTextCharacterName.text = character.name
                    mTextCharacterRace.text = character.race
                    val alignment = "${character.alignment.attitude} ${character.alignment.morality}"
                    mTextCharacterAlignment.text = alignment
                    mTextCharacterBackground.text = character.background
                    mTextCharacterClass.text = character.characterClass
                    mTextCharacterExp.text = applicationContext.getString(R.string.nav_character_experience, character.experiencePoints.toString())
                    mTextCharacterLvl.text = applicationContext.getString(R.string.nav_character_level, character.level.toString())

                    characterViewModel.getCharacterPrimaryStats(character.primaryStats)
                        .observe(this, Observer { primaryStats ->
                            if (primaryStats != null) {
                                // Set views with primary information
                                mTextCharacterHp.text = applicationContext.getString(R.string.nav_character_hp,
                                    primaryStats.temporaryHitPoints.toString(),
                                    primaryStats.hitPoints.toString())

                                setPrimaryScoreViewValues(mArmorClassView,"Armor Class", primaryStats.armorClass)
                                setPrimaryScoreViewValues(mInitiativeViewView,"Initiative", primaryStats.initiative)
                                setPrimaryScoreViewValues(mSpeedView,"Speed", primaryStats.speed)
                                setPrimaryScoreViewValues(mProficiencyView,"Proficiency", primaryStats.proficiencyBonus)

                                // region ability views
                                setAbilityViewValues(mStrengthView,
                                    "Strength",
                                    primaryStats.abilities.strength.abilityScore,
                                    primaryStats.abilities.strength.abilityModifier,
                                    primaryStats.abilities.strength.passiveCheck)

                                setAbilityViewValues(mDexterityView,
                                    "Dexterity",
                                    primaryStats.abilities.dexterity.abilityScore,
                                    primaryStats.abilities.dexterity.abilityModifier,
                                    primaryStats.abilities.dexterity.passiveCheck)

                                setAbilityViewValues(mConstitutionView,
                                    "Constitution",
                                    primaryStats.abilities.constitution.abilityScore,
                                    primaryStats.abilities.constitution.abilityModifier,
                                    primaryStats.abilities.constitution.passiveCheck)

                                setAbilityViewValues(mIntelligenceView,
                                    "Intelligence",
                                    primaryStats.abilities.intelligence.abilityScore,
                                    primaryStats.abilities.intelligence.abilityModifier,
                                    primaryStats.abilities.intelligence.passiveCheck)

                                setAbilityViewValues(mWisdomView,
                                    "Wisdom",
                                    primaryStats.abilities.wisdom.abilityScore,
                                    primaryStats.abilities.wisdom.abilityModifier,
                                    primaryStats.abilities.wisdom.passiveCheck)

                                setAbilityViewValues(mCharismaView,
                                    "Charisma",
                                    primaryStats.abilities.charisma.abilityScore,
                                    primaryStats.abilities.charisma.abilityModifier,
                                    primaryStats.abilities.charisma.passiveCheck)
                                //endregion
                            }
                        })
                })
        }
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun updateUI(currentUser: User?) {
        if (currentUser != null) {
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_name).text = currentUser.email
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_class).text = currentUser.name
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_level).text = currentUser.uid
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_xp).text = currentUser.id
        }
    }

    private fun setPrimaryScoreViewValues(scoreView: View, scoreName: String, scoreValue: Int) {
        scoreView.findViewById<TextView>(R.id.text_generic_card_title).text = scoreName
        scoreView.findViewById<TextView>(R.id.text_generic_card_value).text = scoreValue.toString()
    }

    private fun setAbilityViewValues(abilityView: View, abilityName: String, abilityScore: Int, abilityModifier: Int, abilitySave: Int) {
        abilityView.findViewById<TextView>(R.id.text_character_ability_name).text = abilityName
        abilityView.findViewById<TextView>(R.id.text_character_ability_score).text = abilityScore.toString()
        abilityView.findViewById<TextView>(R.id.text_character_mod_score).text = abilityModifier.toString()
        abilityView.findViewById<TextView>(R.id.text_character_check_score).text = abilitySave.toString()
    }
}
