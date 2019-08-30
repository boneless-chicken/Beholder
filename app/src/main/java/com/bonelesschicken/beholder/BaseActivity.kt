package com.bonelesschicken.beholder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.lang.ref.WeakReference

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mPreferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreferenceManager = PreferenceManager.getInstance(WeakReference(this))
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
}