package com.bonelesschicken.beholder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.google.firebase.auth.FirebaseUser
import androidx.appcompat.app.AlertDialog
import com.bonelesschicken.beholder.data.model.User


class MainActivity : BaseActivity() {
    private lateinit var mToolbar: Toolbar
    private lateinit var mAppBar: AppBarLayout

    private lateinit var mRecyclerCharacters: RecyclerView
    private lateinit var mAdapterCharacters: CharacterListAdapter

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        mAppBar = findViewById(R.id.main_app_bar)
        setSupportActionBar(mToolbar)

        mRecyclerCharacters = findViewById(R.id.recycler_main_characters)
        mAdapterCharacters = CharacterListAdapter(this, ArrayList())

        mRecyclerCharacters.setHasFixedSize(true)
        mRecyclerCharacters.layoutManager = LinearLayoutManager(this)
        mRecyclerCharacters.adapter = mAdapterCharacters

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)

        mainViewModel.getCharacters(mSessionManager.user!!.uid)
            .observe(this@MainActivity, Observer {
                val characterList = it ?: return@Observer
                mAdapterCharacters.setData(characterList)
            })
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
        } else {
            mToolbar.title = getString(R.string.app_name)
        }
    }

    private fun showSignOutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.action_logout))
            .setMessage(getString(R.string.action_dialog_sure_to_logout))
            .setPositiveButton(android.R.string.yes) { _, _ ->
                mSessionManager.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

}
