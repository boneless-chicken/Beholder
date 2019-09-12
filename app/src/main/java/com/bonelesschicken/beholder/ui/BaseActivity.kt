package com.bonelesschicken.beholder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bonelesschicken.beholder.utils.PreferenceManager
import java.lang.ref.WeakReference
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.auth.FirebaseUser


abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mPreferenceManager: PreferenceManager
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPreferenceManager = PreferenceManager.getInstance(WeakReference(this))
        mAuth = FirebaseAuth.getInstance()

        applyTheme()
    }

    private fun applyTheme() {
        if (mPreferenceManager.loadDarkThemeConfig(this)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun alternateTheme() {
        val actualTheme = mPreferenceManager.loadDarkThemeConfig(this)
        mPreferenceManager.saveDarkThemeConfig(!actualTheme, this)
        applyTheme()
    }

    abstract fun updateUI(currentUser: FirebaseUser?)
}