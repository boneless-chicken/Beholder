package com.bonelesschicken.beholder.utils

import android.content.Context
import java.lang.ref.WeakReference

class PreferenceManager private constructor(var context: WeakReference<Context>) {

    companion object : SingletonHolder<PreferenceManager, WeakReference<Context>>(::PreferenceManager)
    /**
     * Saves the configuration of the theme selected.
     * @param pref boolean with the preference
     */
    fun saveDarkThemeConfig(pref: Boolean, context: Context) {
        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        val editor = mSharedPref!!.edit()
        editor.putBoolean("DARK_THEME", pref)
        editor.apply()
    }

    /**
     * Loads the dark theme configuration
     * @return a boolean with the configuration
     */
    fun loadDarkThemeConfig(context: Context): Boolean {
        val mSharedPref = context.getSharedPreferences("PREFERENCES_BEHOLDER", Context.MODE_PRIVATE)
        return mSharedPref!!.getBoolean("DARK_THEME", false)
    }
}