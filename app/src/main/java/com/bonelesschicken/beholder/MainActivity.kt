package com.bonelesschicken.beholder

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class MainActivity : BaseActivity() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mAppBar: AppBarLayout

    private lateinit var mRecyclerCharacters: RecyclerView
    private lateinit var mAdapterCharacters: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        mAppBar = findViewById(R.id.main_app_bar)
        setSupportActionBar(mToolbar)

        mRecyclerCharacters = findViewById(R.id.recycler_main_characters)
        mAdapterCharacters = CharacterListAdapter(this,
            arrayListOf(Character("Trombadin", "Eneano", 14),
                Character("Gandalf", "Mago", 12),
                Character("Sauron", "Malo malote", 666),
                Character("Frodo", "Hobbit", 2),
                Character("Sneaky", "Trapito", 69),
                Character("Chango", "Primate", 0),
                Character("Legonas", "Elfo", 89),
                Character("El Emperador", "Ruco", 66),
                Character("Fernando", "Maricon", -1),
                Character("Brian", "Diabetin", 76)))

        mRecyclerCharacters.setHasFixedSize(true)
        mRecyclerCharacters.layoutManager = LinearLayoutManager(this)
        mRecyclerCharacters.adapter = mAdapterCharacters
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_dark_theme) {
            alternateTheme()
        }

        return super.onOptionsItemSelected(item)
    }
}
