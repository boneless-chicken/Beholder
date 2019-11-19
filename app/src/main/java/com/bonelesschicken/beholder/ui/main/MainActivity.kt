package com.bonelesschicken.beholder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bonelesschicken.beholder.ui.BaseActivity
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.ui.login.LoginActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import com.bonelesschicken.beholder.data.model.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


class MainActivity : BaseActivity() {
    private lateinit var mNavigationView: NavigationView
    private lateinit var mNavigationHeader: View
    private lateinit var mDrawerLayout: DrawerLayout

    private lateinit var mToolbar: Toolbar
    private lateinit var mAppBar: AppBarLayout

    private lateinit var mRecyclerCharacters: RecyclerView
    private lateinit var mAdapterCharacters: CharacterListAdapter

    private lateinit var mainViewModel: MainViewModel

    private lateinit var mFabMain: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        mAppBar = findViewById(R.id.main_app_bar)
        setSupportActionBar(mToolbar)

        mDrawerLayout = findViewById(R.id.drawer_main_layout)
        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById(R.id.nav_view_main)
        mNavigationHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_character)

        mRecyclerCharacters = findViewById(R.id.recycler_main_characters)
        mAdapterCharacters = CharacterListAdapter(this, ArrayList(), this)

        mRecyclerCharacters.setHasFixedSize(true)
        mRecyclerCharacters.layoutManager = LinearLayoutManager(this)
        mRecyclerCharacters.adapter = mAdapterCharacters

        mFabMain = findViewById(R.id.fab_button_main)
        mFabMain.setOnClickListener {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show()
        }

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)

        if (mSessionManager.user != null) {
            mainViewModel.getCharacters(mSessionManager.user!!.uid)
                .observe(this@MainActivity, Observer {
                    val characterList = it ?: return@Observer
                    mAdapterCharacters.setData(characterList)
                })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_dark_theme) {
            alternateTheme()
        } else if (id == R.id.action_login) {
            if (FirebaseAuth.getInstance().currentUser != null) {
                showSignOutDialog()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun updateUI(currentUser: User?) {
        if (currentUser != null) {
            supportActionBar?.title = currentUser.email
            mNavigationHeader.findViewById<TextView>(R.id.text_nav_character_name).text = currentUser.email
        } else {
            mToolbar.title = getString(R.string.app_name)
        }
    }

    private fun showSignOutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.action_logout))
            .setMessage(getString(R.string.action_dialog_sure_to_logout))
            .setPositiveButton(android.R.string.yes) { _, _ ->
                finish()
                mSessionManager.logout()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

}
