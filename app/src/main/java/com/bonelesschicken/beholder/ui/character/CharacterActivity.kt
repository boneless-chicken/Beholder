package com.bonelesschicken.beholder.ui.character

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.ui.BaseActivity
import com.bonelesschicken.beholder.utils.BottomAppBarCutCornersTopEdge
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.tabs.TabLayout

class CharacterActivity : BaseActivity() {

    companion object {
        const val CHARACTER_ID = "CHARACTER_ID"
    }

    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavigationHeader: View

    private lateinit var mAppBarLayout: AppBarLayout
    private lateinit var mToolbar : Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: CharacterFragmentPagerAdapter

    private lateinit var mTextCharacterName: TextView
    private lateinit var mTextCharacterClass: TextView

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mBottomAppBar: BottomAppBar
    private lateinit var mFabButton: FloatingActionButton

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        setupBottomAppBar()

        mAppBarLayout = findViewById(R.id.character_app_bar)
        mToolbar = findViewById(R.id.character_toolbar)

        mTabLayout = findViewById(R.id.character_tabs)

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, mBottomAppBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById(R.id.nav_view)
        mNavigationHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_character)

        characterViewModel = ViewModelProvider(this, CharacterViewModelFactory(this))
            .get(CharacterViewModel::class.java)


        mTextCharacterName = findViewById(R.id.text_character_name)
        mTextCharacterClass = findViewById(R.id.text_character_race)

        if (intent.hasExtra(CHARACTER_ID)) {
            mViewPager = findViewById(R.id.character_view_pager)
            mViewPagerAdapter = CharacterFragmentPagerAdapter(supportFragmentManager, intent.getStringExtra(CHARACTER_ID))
            mViewPager.adapter = mViewPagerAdapter
            mTabLayout.setupWithViewPager(mViewPager)
            characterViewModel.getCharacter(intent.getStringExtra(CHARACTER_ID))
                .observe(this, Observer {
                    mTextCharacterName.text = it.characterInfo.name
                    mTextCharacterClass.text = it.characterInfo.className
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
}
