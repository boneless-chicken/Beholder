package com.bonelesschicken.beholder.ui.character

import android.os.Bundle
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

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        mBottomAppBar = findViewById(R.id.bar)
        mFabButton = findViewById(R.id.fab)

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

        // Views
        mTextCharacterName = findViewById(R.id.text_character_name)
        mTextCharacterRace = findViewById(R.id.text_character_race)
        mTextCharacterAlignment = findViewById(R.id.text_character_alignment)
        mTextCharacterBackground = findViewById(R.id.text_character_background)
        mTextCharacterClass = findViewById(R.id.text_character_class)
        mTextCharacterExp = findViewById(R.id.text_character_exp)
        mTextCharacterLvl = findViewById(R.id.text_character_level)

        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(this)).get(CharacterViewModel::class.java)

        if (intent.hasExtra(CHARACTER_ID)) {
            characterViewModel.getCharacterDetail(intent.getStringExtra(CHARACTER_ID))
                .observe(this, Observer {
                    // Main views
                    mTextCharacterName.text = it.name
                    mTextCharacterRace.text = it.race
                    val alignment = "${it.alignment.attitude} ${it.alignment.morality}"
                    mTextCharacterAlignment.text = alignment
                    mTextCharacterBackground.text = it.background
                    mTextCharacterClass.text = it.characterClass
                    mTextCharacterExp.text = it.experiencePoints.toString()
                    mTextCharacterLvl.text = it.level.toString()
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

}
