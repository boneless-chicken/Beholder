package com.bonelesschicken.beholder.ui.character

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bonelesschicken.beholder.R
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
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mTextCharacterName: TextView

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val bar = findViewById<BottomAppBar>(R.id.bar)
        val fab2 = findViewById<FloatingActionButton>(R.id.fab)

        mTextCharacterName = findViewById(R.id.text_character_detail_name)

        val topEdge = BottomAppBarCutCornersTopEdge(
            bar.fabCradleMargin,
            bar.fabCradleRoundedCornerRadius,
            bar.cradleVerticalOffset
        )
        val babBackground = bar.background as MaterialShapeDrawable
        babBackground.shapeAppearanceModel.topEdge = topEdge
        babBackground.invalidateSelf()

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById(R.id.nav_view)
        val mNavHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_character)
        val mNavCharacterName = mNavHeader.findViewById<TextView>(R.id.text_nav_character_name)
        val mNavCharacterClass = mNavHeader.findViewById<TextView>(R.id.text_nav_character_class)
        val mNavCharacterLevel = mNavHeader.findViewById<TextView>(R.id.text_nav_character_level)
        val mNavCharacterExperience = mNavHeader.findViewById<TextView>(R.id.text_nav_character_xp)

        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory()).get(CharacterViewModel::class.java)

        if (intent.hasExtra(CHARACTER_ID)) {
            characterViewModel.getCharacterDetail(intent.getLongExtra(CHARACTER_ID, 0L))
                .observe(this, Observer {
                    // Nav views
                    mNavCharacterName.text = it.name
                    mNavCharacterClass.text = it.className
                    mNavCharacterLevel.text = applicationContext.getString(R.string.nav_character_level, it.level.toString())
                    mNavCharacterExperience.text = applicationContext.getString(R.string.nav_character_experience, it.experience.toString())

                    // Main views
                    mTextCharacterName.text = it.name
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

}
