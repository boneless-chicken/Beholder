package com.bonelesschicken.beholder.ui.character

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        setupBottomAppBar()

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
                        .observe(this, Observer { relation ->
                            if (relation != null) {
                                // Set views with primary information
                                mTextCharacterHp.text = applicationContext.getString(R.string.nav_character_hp,
                                    relation.primaryStats?.hitPoints?.currentHitPoints.toString(),
                                    relation.primaryStats?.hitPoints?.totalHitPoints.toString())

                                setPrimaryScoreViewValues(mArmorClassView,"Armor Class", relation.primaryStats?.armorClass)
                                setPrimaryScoreViewValues(mInitiativeViewView,"Initiative", relation.primaryStats?.initiative)
                                setPrimaryScoreViewValues(mSpeedView,"Speed", relation.primaryStats?.speed)
                                setPrimaryScoreViewValues(mProficiencyView,"Proficiency", relation.primaryStats?.proficiencyBonus)

                                // region ability views
                                setAbilityViewValues(mStrengthView,
                                    "Strength",
                                    relation.primaryStats?.abilities?.strength?.abilityScore,
                                    relation.primaryStats?.abilities?.strength?.abilityModifier,
                                    relation.primaryStats?.abilities?.strength?.passiveCheck)

                                setAbilityViewValues(mDexterityView,
                                    "Dexterity",
                                    relation.primaryStats?.abilities?.dexterity?.abilityScore,
                                    relation.primaryStats?.abilities?.dexterity?.abilityModifier,
                                    relation.primaryStats?.abilities?.dexterity?.passiveCheck)

                                setAbilityViewValues(mConstitutionView,
                                    "Constitution",
                                    relation.primaryStats?.abilities?.constitution?.abilityScore,
                                    relation.primaryStats?.abilities?.constitution?.abilityModifier,
                                    relation.primaryStats?.abilities?.constitution?.passiveCheck)

                                setAbilityViewValues(mIntelligenceView,
                                    "Intelligence",
                                    relation.primaryStats?.abilities?.intelligence?.abilityScore,
                                    relation.primaryStats?.abilities?.intelligence?.abilityModifier,
                                    relation.primaryStats?.abilities?.intelligence?.passiveCheck)

                                setAbilityViewValues(mWisdomView,
                                    "Wisdom",
                                    relation.primaryStats?.abilities?.wisdom?.abilityScore,
                                    relation.primaryStats?.abilities?.wisdom?.abilityModifier,
                                    relation.primaryStats?.abilities?.wisdom?.passiveCheck)

                                setAbilityViewValues(mCharismaView,
                                    "Charisma",
                                    relation.primaryStats?.abilities?.charisma?.abilityScore,
                                    relation.primaryStats?.abilities?.charisma?.abilityModifier,
                                    relation.primaryStats?.abilities?.charisma?.passiveCheck)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.character_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_level_up) {
            Toast.makeText(this, "Level up", Toast.LENGTH_SHORT).show()
            return true
        } else if (id == R.id.action_rest) {
            Toast.makeText(this, "Rest", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateUI(currentUser: User?) {
        if (currentUser != null) {
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_name).text = currentUser.email
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_class).text = currentUser.name
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_level).text = ""
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_xp).text = ""
        }
    }

    private fun setupBottomAppBar() {
        mBottomAppBar = findViewById(R.id.bottom_bar_character)
        mFabButton = findViewById(R.id.fab_button_character)
        setSupportActionBar(mBottomAppBar)

        mBottomAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Nav clicked", Toast.LENGTH_SHORT).show()
        }

        val topEdge = BottomAppBarCutCornersTopEdge(
            mBottomAppBar.fabCradleMargin,
            mBottomAppBar.fabCradleRoundedCornerRadius,
            mBottomAppBar.cradleVerticalOffset
        )
        val babBackground = mBottomAppBar.background as MaterialShapeDrawable
        babBackground.shapeAppearanceModel = babBackground.shapeAppearanceModel.toBuilder().setTopEdge(topEdge).build()
        babBackground.invalidateSelf()
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
