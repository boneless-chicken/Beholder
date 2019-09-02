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
import com.bonelesschicken.beholder.data.model.Character
import com.bonelesschicken.beholder.R
import com.bonelesschicken.beholder.ui.login.LoginActivity
import com.google.android.material.appbar.AppBarLayout

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

        mainViewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)

        mainViewModel.characterList.observe(this@MainActivity, Observer {
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
            startActivity(Intent(this, LoginActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}
