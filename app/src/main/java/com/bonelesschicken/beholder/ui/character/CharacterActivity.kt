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
import com.bonelesschicken.beholder.ui.main.MainViewModel
import com.bonelesschicken.beholder.ui.main.MainViewModelFactory
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

        bar.setNavigationOnClickListener {

        }

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, bar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById(R.id.nav_view)
        val navHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_character)
        /*val mNavHeaderProfileName = navHeader.findViewById<TextView>(R.id.text_nav_header_title)
        val mNavHeaderProfileEmail = navHeader.findViewById<TextView>(R.id.text_nav_header_subtitle)
        if (mNavHeaderProfileName != null && mNavHeaderProfileEmail != null) {
            mNavHeaderProfileName.text = mSession.session.name
            mNavHeaderProfileEmail.text = mSession.session.email
        }*/

        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory()).get(CharacterViewModel::class.java)

        if (intent.hasExtra(CHARACTER_ID)) {
            characterViewModel.getCharacterDetail(intent.getLongExtra(CHARACTER_ID, 0L))
                .observe(this, Observer {
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
