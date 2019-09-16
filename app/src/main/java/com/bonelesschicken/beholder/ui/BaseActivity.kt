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
import com.bonelesschicken.beholder.data.model.User
import com.bonelesschicken.beholder.utils.SessionManager
import com.google.firebase.auth.FirebaseUser


abstract class BaseActivity : AppCompatActivity() {
    lateinit var mSessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSessionManager = SessionManager(this)
        applyTheme()
    }

    override fun onStart() {
        super.onStart()
        updateUI(mSessionManager.user)
    }

    private fun applyTheme() {
        if (PreferenceManager.loadDarkThemeConfig(this)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun alternateTheme() {
        val actualTheme = PreferenceManager.loadDarkThemeConfig(this)
        PreferenceManager.saveDarkThemeConfig(!actualTheme, this)
        applyTheme()
    }

    abstract fun updateUI(currentUser: User?)
}